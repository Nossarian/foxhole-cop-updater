import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder
import groovy.xml.XmlParser
import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil
import groovy.xml.slurpersupport.GPathResult
import groovy.xml.slurpersupport.Node

import javax.xml.namespace.QName
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class toMilX {

    static void main(String... args){
        println("Hex Width: " + MapRegions.w)
        println("Map Width: " + MapRegions.w/2*11)
        println("Hex Height: " + MapRegions.k)
        println("Map Height: " + MapRegions.k*7)
        println("Map Origin: " + MapRegions.mapOrigin)
        def warNum = getApiAsJson('https://war-service-live.foxholeservices.com/api/worldconquest/war').warNumber
        def timestamp = new Date().format("dd-MM-yyyy HH.mm.ss")
        def directoryName = "../War $warNum milxOut_" + timestamp
        File directory = new File(directoryName)
        if(!directory.exists()){
            directory.mkdir()
        }
        println(directory.absolutePath)
        File output = new File("./$directoryName/Permanent Structures.milxly")
        File resourcesLayer =  new File("./$directoryName/Resources.milxly")
        File aiLayer = new File("./$directoryName/AILayer.milxly")
        File scRanges = new File("./$directoryName/SCRanges.milxly")
        File basePins = new File("./$directoryName/Base Pins.milxly")
        println(output.absolutePath)
        println(resourcesLayer.absolutePath)
        println(aiLayer.absolutePath)
        println(scRanges.absolutePath)
        println(basePins.absolutePath)
        println(System.getProperty("java.class.path"))
        println(args)
        def apiArr = generateApi()
        output.text = generateMilX(apiArr,args[0].toBoolean())
        resourcesLayer.text = generateResourceNodes(apiArr,args[1].toBoolean())
        aiLayer.text = generateAIRanges(apiArr,args[2].toBoolean(), args[0].toBoolean())
        scRanges.text = generateSCRanges(apiArr,args[3].toBoolean(), args[0].toBoolean())
        basePins.text = generateBasePins(apiArr,args[4].toBoolean())
//                output.text = generateMilX(true)
//        resourcesLayer.text = generateResourceNodes(true)
//        aiLayer.text = generateAIRanges(true, true)
//        scRanges.text = generateSCRanges(true, true)
        def date = new Date()
        def sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss:ms")
        println(sdf.format(date))
    }

    static siegeCampGet = 'https://war-service-live.foxholeservices.com/api'

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
                addStormCannonRanges(hex.mapItems, MilXLayerDocument, hex.regionId, [300,1000,1300], factions)
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

                        DecimalFormat formatter = new DecimalFormat("#.################")
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

                DecimalFormat formatter = new DecimalFormat("#.################")
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

    static addStormCannonRanges(mapItems, documentLayer, regionId, rangeIncrements=[300,1000,1300], factions = true){
        def xMod =0.9995244413
        def yMod =0.9988194796
        mapItems.mapItems.each { mapItem ->
            def MssStringXML = MapIconToMilX.getStormCannonRangesFromAPI(mapItem, factions)
            if (MssStringXML) {
                def x = mapItem.x
                def y = mapItem.y
                def latLong = MapRegions.fullConvert(regionId, x, y)

                DecimalFormat formatter = new DecimalFormat("#.################")
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

                DecimalFormat formatter = new DecimalFormat("#.################")
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
                def x = mapItem.x
                def y = mapItem.y
                def latLong = MapRegions.fullConvert(regionId, x, y)

                DecimalFormat formatter = new DecimalFormat("#.################")
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
