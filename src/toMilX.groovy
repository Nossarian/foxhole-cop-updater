import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder
import groovy.xml.XmlParser
import groovy.xml.XmlUtil

import java.text.DecimalFormat
import java.text.SimpleDateFormat

class toMilX {

    static void main(String... args = []){
        println("Hex Width: " + MapRegions.w)
        println("Map Width: " + MapRegions.w/2*11)
        println("Hex Height: " + MapRegions.k)
        println("Map Height: " + MapRegions.k*7)
        println("Map Origin: " + MapRegions.mapOrigin)
        def warNum = getApiAsJson('https://war-service-live.foxholeservices.com/api/worldconquest/war').warNumber
        def timestamp = new Date().format("dd-MM-yyyy HH.mm.ss")
        def directoryName = "../War $warNum/milxOut_" + timestamp
        File directory = new File(directoryName)
        if(!directory.exists()){
            directory.mkdirs()
        }
        def staticDir = "../Static Layers"
        File staticLayerDir = new File(staticDir)
        if(!staticLayerDir.exists()){
            staticLayerDir.mkdirs()
        }
        println(directory.absolutePath)
        File output = new File("./$directoryName/Permanent Structures.milxly")
        File resourcesLayer =  new File("./../War $warNum/Resources.milxly")
        File aiLayer = new File("./$directoryName/AILayer.milxly")
        File scRanges = new File("./$directoryName/SCRanges.milxly")
        File icRanges = new File("./$directoryName/ICRanges.milxly")
        File basePins = new File("./$staticLayerDir/Base Pins.milxly")
        File rdzExceptions = new File("./$directoryName/RDZ Exceptions.milxly")
        File textLabels = new File("./$staticLayerDir/Text Labels.milxly")
        println("Writing static layers to: $staticLayerDir.absolutePath")
        println("Writing dynamic layers to: $directory.absolutePath")
        println(args)
        def apiArr = generateApi()
        if(args.length != 0){
            output.text = generateMilX(apiArr,args[0].toBoolean())
            resourcesLayer.text = generateResourceNodes(apiArr,args[1].toBoolean())
            rdzExceptions.text = generateRdzExceptions(apiArr)
            aiLayer.text = generateAIRanges(apiArr,args[2].toBoolean(), args[0].toBoolean())
            scRanges.text = generateSCRanges(apiArr,args[3].toBoolean(), args[0].toBoolean())
            icRanges.text = generateICRanges(apiArr,args[3].toBoolean(), args[0].toBoolean())
            basePins.text = generateBasePins(apiArr,args[4].toBoolean())
        } else {
            output.text = generateMilX(apiArr,true)
            resourcesLayer.text = generateResourceNodes(apiArr,true)
            aiLayer.text = generateAIRanges(apiArr,true, true)
            scRanges.text = generateSCRanges(apiArr,true, true)
            icRanges.text = generateICRanges(apiArr,true, true)
            textLabels.text = generateTextLabels(apiArr)
            rdzExceptions.text = generateRdzExceptions(apiArr)
            basePins.text = generateBasePins(apiArr,true)
        }


        def date = new Date()
        def sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss:ms")
        println(sdf.format(date))
    }

    static siegeCampGet = 'https://war-service-live.foxholeservices.com/api'
    static DecimalFormat formatter = new DecimalFormat("#.###############################")

    static generateApi(){
        def result = []
        def mapList = getApiAsJson(siegeCampGet + "/worldconquest/maps")
        mapList.each {hex ->
            def hexMap = [:]
            def staticMapItems = getApiAsJson(siegeCampGet + "/worldconquest/maps/$hex/static")
            def mapItems = getApiAsJson(siegeCampGet + "/worldconquest/maps/$hex/dynamic/public")
            def regionId = mapItems.regionId
            hexMap.put("staticMapItems", staticMapItems)
            hexMap.put("mapItems", mapItems)
            hexMap.put("regionId", regionId)
            result.add(hexMap)
//            println(hex)
//            staticMapItems.mapTextItems.each{it ->
//                if(it.mapMarkerType.toString() == "Major"){
//                    println(it.text)
//                }
//            }
        }
        return result
    }

    static generateMilX(apiArr,factions = true) {
        println("Including Faction Colors: $factions")
        def parser = new XmlParser()
        def writer  = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
        xml.MilXDocument_Layer(
                xmlns: "http://gs-soft.com/MilX/V3.1"
        ) {
            MssLibraryVersionTag('2021.04.20')
            MilXLayer() {
                Name('Permanent Structures')
                LayerType('Normal')
                GraphicList() {

                }
                CoordSystemType('WGS84')
                ViewScale('0.1')
            }
        }
        def MilXLayerDocument = parser.parseText(writer.toString())

        apiArr.each{ hex ->
            addMilXGraphic(hex.staticMapItems, hex.mapItems, MilXLayerDocument, hex.regionId, factions)
        }

        return XmlUtil.serialize(MilXLayerDocument)
    }
    static generateBasePins(apiArr, Boolean pins) {
        println("Including Base Pins: $pins")
        def parser = new XmlParser()
        def writer  = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
        xml.MilXDocument_Layer(
                xmlns: "http://gs-soft.com/MilX/V3.1"
        ) {
            MssLibraryVersionTag('2021.04.20')
            MilXLayer() {
                Name('Base Pins')
                LayerType('Normal')
                GraphicList() {

                }
                CoordSystemType('WGS84')
                ViewScale('0.1')
            }
        }
        def MilXLayerDocument = parser.parseText(writer.toString())

        apiArr.each{ hex ->
            addMilXBasePins(hex.staticMapItems, hex.mapItems, MilXLayerDocument, hex.regionId)
        }

        return XmlUtil.serialize(MilXLayerDocument)
    }
    static generateTextLabels(apiArr) {
        def parser = new XmlParser()
        def writer  = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
        xml.MilXDocument_Layer(
                xmlns: "http://gs-soft.com/MilX/V3.1"
        ) {
            MssLibraryVersionTag('2021.04.20')
            MilXLayer() {
                Name('Text Labels')
                LayerType('Normal')
                GraphicList() {

                }
                CoordSystemType('WGS84')
                ViewScale('0.1')
            }
        }
        def MilXLayerDocument = parser.parseText(writer.toString())

        apiArr.each{ hex ->
            addStaticMapLabels(hex.staticMapItems, MilXLayerDocument, hex.regionId)
        }

        return XmlUtil.serialize(MilXLayerDocument)
    }

    static generateAIRanges(apiArr,aiRanges = false, factions = true){
        if(aiRanges){
            println("Including AI Ranges: $aiRanges")
            def parser = new XmlParser()
            def writer  = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
            xml.MilXDocument_Layer(
                    xmlns: "http://gs-soft.com/MilX/V3.1"
            ) {
                MssLibraryVersionTag('2021.04.20')
                MilXLayer() {
                    Name('AI Ranges')
                    LayerType('Normal')
                    GraphicList() {

                    }
                    CoordSystemType('WGS84')
                    ViewScale('0.1')
                }
            }
            def MilXLayerDocument = parser.parseText(writer.toString())

            apiArr.each{ hex ->
                addMilXAIRange(hex.mapItems, MilXLayerDocument, hex.regionId, factions)
            }

            return XmlUtil.serialize(MilXLayerDocument)
        }
    }
    static generateRdzExceptions(apiArr, rdzException = true){
        if(rdzException){
            println("Including RDZ Exceptions: $rdzException")
            def parser = new XmlParser()
            def writer  = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
            xml.MilXDocument_Layer(
                    xmlns: "http://gs-soft.com/MilX/V3.1"
            ) {
                MssLibraryVersionTag('2021.04.20')
                MilXLayer() {
                    Name('RDZ Exceptions')
                    LayerType('Normal')
                    GraphicList() {

                    }
                    CoordSystemType('WGS84')
                    ViewScale('0.1')
                }
            }
            def MilXLayerDocument = parser.parseText(writer.toString())

            apiArr.each{ hex ->
                addMilXRDZExceptionRange(hex.mapItems, MilXLayerDocument, hex.regionId)
            }

            return XmlUtil.serialize(MilXLayerDocument)
        }
    }
    static generateSCRanges(apiArr,scRanges = false, factions = true){
        if(scRanges){
            println("Including SC Ranges: $scRanges")
            def parser = new XmlParser()
            def writer  = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
            xml.MilXDocument_Layer(
                    xmlns: "http://gs-soft.com/MilX/V3.1"
            ) {
                MssLibraryVersionTag('2021.04.20')
                MilXLayer() {
                    Name('SC Ranges')
                    LayerType('Normal')
                    GraphicList() {

                    }
                    CoordSystemType('WGS84')
                    ViewScale('0.1')
                }
            }
            def MilXLayerDocument = parser.parseText(writer.toString())

            apiArr.each{ hex ->
                addMegaStructureRanges(hex.mapItems, MilXLayerDocument, hex.regionId, [300, 1000, 1300], "SC", factions)
            }

            return XmlUtil.serialize(MilXLayerDocument)
        }
    }

    static generateICRanges(apiArr, icRanges = false, factions = true){
        if(icRanges){
            println("Including IC Ranges: $icRanges")
            def parser = new XmlParser()
            def writer  = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
            xml.MilXDocument_Layer(
                    xmlns: "http://gs-soft.com/MilX/V3.1"
            ) {
                MssLibraryVersionTag('2021.04.20')
                MilXLayer() {
                    Name('IC Ranges')
                    LayerType('Normal')
                    GraphicList() {

                    }
                    CoordSystemType('WGS84')
                    ViewScale('0.1')
                }
            }
            def MilXLayerDocument = parser.parseText(writer.toString())

            apiArr.each{ hex ->
                addMegaStructureRanges(hex.mapItems, MilXLayerDocument, hex.regionId, [100, 1000, 2000], "IC", factions)
            }

            return XmlUtil.serialize(MilXLayerDocument)
        }
    }

    static generateResourceNodes(apiArr,resources = false){
        if(resources){
            println("Including Resources: $resources")
            def parser = new XmlParser()
            def writer  = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.mkp.xmlDeclaration([version:'1.0', encoding:'UTF-8', standalone:'no'])
            xml.MilXDocument_Layer(
                    xmlns: "http://gs-soft.com/MilX/V3.1"
            ) {
                MssLibraryVersionTag('2021.04.20')
                MilXLayer() {
                    Name('Resource Nodes')
                    LayerType('Normal')
                    GraphicList() {

                    }
                    CoordSystemType('WGS84')
                    ViewScale('0.1')
                }
            }
            def MilXLayerDocument = parser.parseText(writer.toString())

            apiArr.each{ hex ->
                addMilXResources(hex.mapItems, MilXLayerDocument, hex.regionId, resources)
            }

            return XmlUtil.serialize(MilXLayerDocument)
        }
    }

    static getApiAsJson(url){
        def get = new URL(url).openConnection()
        def getRC = get.getResponseCode()
        def response = new JsonSlurper().parseText(get.getInputStream().getText())
        return response
    }

    static addMilXResources(mapItems, documentLayer, regionId, resources = false){
        def xMod =0.9995244413
        def yMod =0.9988194796
        def radius = 15
        mapItems.mapItems.each { mapItem ->
            def MssStringXMLArray = MapIconToMilX.getResourceNodeNoBuild(mapItem)
            if (MssStringXMLArray) {
                MssStringXMLArray.each{
                    MssStringXML ->
                        def x = mapItem.x
                        def y = mapItem.y
                        def latLong = MapRegions.fullConvert(regionId, x, y)

                        latLong.lon = formatter.format(latLong.lon * xMod)
                        latLong.lat = formatter.format(latLong.lat * yMod)

                        documentLayer.value()[1].value()[2].appendNode(
                                "MilXGraphic",
                                [:]
                        )
                        def size = documentLayer.value()[1].value()[2].value().size()
                        documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                                "MssStringXML",
                                [:],
                                MssStringXML
                        )
                        documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                                "PointList",
                                [:]
                        )
                        documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                                "Point",
                                [:]
                        )
                        documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                                "X",
                                [:],
                                latLong.lon
                        )
                        documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                                "Y",
                                [:],
                                latLong.lat
                        )
                        if(MssStringXMLArray.findIndexOf {it -> it == MssStringXML && it != ""} == 1){
                            documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                                    "LocationAttributeList",
                                    [:]
                            )
                            documentLayer.value()[1].value()[2].value()[size - 1].value()[2].appendNode(
                                    "LocationAttribute",
                                    [:]
                            )
                            documentLayer.value()[1].value()[2].value()[size - 1].value()[2].value()[0].appendNode(
                                    "AttrType",
                                    [:],
                                    "Radius"
                            )
                            documentLayer.value()[1].value()[2].value()[size - 1].value()[2].value()[0].appendNode(
                                    "Value",
                                    [:],
                                    radius
                            )
                        }

                }
            }
        }

    }
    static addMilXRDZExceptionRange(mapItems, documentLayer, regionId){
        def xMod =0.9995244413
        def yMod =0.9988194796
        def radius = 55
        mapItems.mapItems.each { mapItem ->
            def MssStringXML = MapIconToMilX.getRdzExceptionSymbolsFromAPI(mapItem)
            if (MssStringXML) {
                def x = mapItem.x
                def y = mapItem.y
                def iconTypeKeyRadiusValue = [
                        [33:63],
                        [35:63],
                        [45:58],
                        [46:60],
                        [47:62],
                        [56:50],//presumed TB
                        [57:50],//presumed TB
                        [58:50]//presumed TB
                ]
                def mapResult = iconTypeKeyRadiusValue.find{it.key == mapItem.iconType}
                if( mapResult != null && mapResult > 0){
                    radius = mapResult.value
                }
                def latLong = MapRegions.fullConvert(regionId, x, y)

                latLong.lon = formatter.format(latLong.lon * xMod)
                latLong.lat = formatter.format(latLong.lat * yMod)

                documentLayer.value()[1].value()[2].appendNode(
                        "MilXGraphic",
                        [:]
                )
                def size = documentLayer.value()[1].value()[2].value().size()
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "MssStringXML",
                        [:],
                        MssStringXML
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "PointList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                        "Point",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "X",
                        [:],
                        latLong.lon
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "Y",
                        [:],
                        latLong.lat
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "LocationAttributeList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[2].appendNode(
                        "LocationAttribute",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[2].value()[0].appendNode(
                        "AttrType",
                        [:],
                        "Radius"
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[2].value()[0].appendNode(
                        "Value",
                        [:],
                        radius
                )
            }
        }
    }

    static addMilXAIRange(mapItems, documentLayer, regionId, factions = true){
        def xMod =0.9995244413
        def yMod =0.9988194796
        def radius = 0
        mapItems.mapItems.each { mapItem ->
            def MssStringXML = MapIconToMilX.getAiRangesFromAPI(mapItem, factions)
            if (MssStringXML) {
                def x = mapItem.x
                def y = mapItem.y
                if (mapItem.iconType.toInteger() == 35){
                    radius = 100
                } else {
                    radius = 150
                }
                def latLong = MapRegions.fullConvert(regionId, x, y)

                latLong.lon = formatter.format(latLong.lon * xMod)
                latLong.lat = formatter.format(latLong.lat * yMod)

                documentLayer.value()[1].value()[2].appendNode(
                        "MilXGraphic",
                        [:]
                )
                def size = documentLayer.value()[1].value()[2].value().size()
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "MssStringXML",
                        [:],
                        MssStringXML
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "PointList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                        "Point",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "X",
                        [:],
                        latLong.lon
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "Y",
                        [:],
                        latLong.lat
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "LocationAttributeList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[2].appendNode(
                        "LocationAttribute",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[2].value()[0].appendNode(
                        "AttrType",
                        [:],
                        "Radius"
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[2].value()[0].appendNode(
                        "Value",
                        [:],
                        radius
                )
            }
        }
    }

    static addMegaStructureRanges(mapItems, documentLayer, regionId, rangeIncrements, structure,factions = true){
        def xMod =0.9995244413
        def yMod =0.9988194796
        mapItems.mapItems.each { mapItem ->
            def MssStringXML = MapIconToMilX.getMegaStructureRangesFromAPI(mapItem, structure, factions)
            if (MssStringXML) {
                def x = mapItem.x
                def y = mapItem.y
                def latLong = MapRegions.fullConvert(regionId, x, y)
                latLong.lon = formatter.format(latLong.lon * xMod)
                latLong.lat = formatter.format(latLong.lat * yMod)

                documentLayer.value()[1].value()[2].appendNode(
                        "MilXGraphic",
                        [:]
                )
                def size = documentLayer.value()[1].value()[2].value().size()
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "MssStringXML",
                        [:],
                        MssStringXML
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "PointList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                        "Point",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "X",
                        [:],
                        latLong.lon
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "Y",
                        [:],
                        latLong.lat
                )
                def pointCount = 1
                rangeIncrements.each{it ->
                    def incXY = MapRegions.convertCoords(regionId, mapItem.x, mapItem.y)
                    def incLatLon = MapRegions.meteresToLatLong(incXY[0], incXY[1] + it)
                    incLatLon.lon = formatter.format(incLatLon.lon * xMod)
                    incLatLon.lat = formatter.format(incLatLon.lat * yMod)
                    documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                            "Point",
                            [:]
                    )
                    documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[pointCount].appendNode(
                            "X",
                            [:],
                            incLatLon.lon
                    )
                    documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[pointCount].appendNode(
                            "Y",
                            [:],
                            incLatLon.lat
                    )
                    pointCount++
                }
            }
        }
    }

    static addMilXBasePins (staticMapItems, mapItems, documentLayer, regionId){
        def xMod =0.9995244413
        def yMod =0.9988194796
        mapItems.mapItems.each { mapItem ->
            def MssStringXML = MapIconToMilX.getBasePins(mapItem, MapRegions.findClosest(staticMapItems, mapItem))
            if (MssStringXML) {
                def x = mapItem.x
                def y = mapItem.y
                def latLong = MapRegions.fullConvert(regionId, x, y)
                latLong.lon = formatter.format(latLong.lon * xMod)
                latLong.lat = formatter.format(latLong.lat * yMod)

                documentLayer.value()[1].value()[2].appendNode(
                        "MilXGraphic",
                        [:]
                )
                def size = documentLayer.value()[1].value()[2].value().size()
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "MssStringXML",
                        [:],
                        MssStringXML
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "PointList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                        "Point",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "X",
                        [:],
                        latLong.lon
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "Y",
                        [:],
                        latLong.lat
                )
            }
        }

    }

    static addStaticMapLabels (staticMapItems, documentLayer, regionId){
        def xMod =0.9995244413
        def yMod =0.9988194796
        staticMapItems.mapTextItems.each{ mapItem ->
            def MssStringXML = MapIconToMilX.getTextLabels(mapItem)
            if (MssStringXML) {
                def x = mapItem.x
                def y = mapItem.y
                def latLong = MapRegions.fullConvert(regionId, x, y)
                latLong.lon = formatter.format(latLong.lon * xMod)
                latLong.lat = formatter.format(latLong.lat * yMod)

                documentLayer.value()[1].value()[2].appendNode(
                        "MilXGraphic",
                        [:]
                )
                def size = documentLayer.value()[1].value()[2].value().size()
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "MssStringXML",
                        [:],
                        MssStringXML
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "PointList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                        "Point",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "X",
                        [:],
                        latLong.lon
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "Y",
                        [:],
                        latLong.lat
                )
            }
        }

    }

    static addMilXGraphic (staticMapItems, mapItems, documentLayer, regionId, factions){
        def xMod =0.9995244413
        def yMod =0.9988194796
        mapItems.mapItems.each { mapItem ->
            def MssStringXML = MapIconToMilX.getMilXFromAPI(mapItem, MapRegions.findClosest(staticMapItems, mapItem), factions)
            if (MssStringXML) {
                xMod = 1
                yMod = 1
                def x = mapItem.x
                def y = mapItem.y
//                if(["Callum's Keep", "The Old Captain"].contains(MapRegions.findClosest(staticMapItems, mapItem)) && [56,57,58].contains(mapItem.iconType)){
//                    println(MapRegions.convertCoords(regionId, x, y))
//                }
                //TODO Adjust map +x by 11.689 and -y by 14.696. Found by measuring callum's to captain, adjusting by the difference, figuring out the radio of how much was left, and adjusting by that match the next time.
                def latLong = MapRegions.fullConvert(regionId, x, y)
                latLong.lon = formatter.format(latLong.lon * xMod)
                latLong.lat = formatter.format(latLong.lat * yMod)

                documentLayer.value()[1].value()[2].appendNode(
                        "MilXGraphic",
                        [:]
                )
                def size = documentLayer.value()[1].value()[2].value().size()
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "MssStringXML",
                        [:],
                        MssStringXML
                )
                documentLayer.value()[1].value()[2].value()[size - 1].appendNode(
                        "PointList",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].appendNode(
                        "Point",
                        [:]
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "X",
                        [:],
                        latLong.lon
                )
                documentLayer.value()[1].value()[2].value()[size - 1].value()[1].value()[0].appendNode(
                        "Y",
                        [:],
                        latLong.lat
                )
            }
        }

    }
}
