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
        File output = new File("./Layer.milxly")
        output.text = generateMilX()
        def date = new Date()
        def sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss:ms")
        println(sdf.format(date))
        println(System.getProperty("java.class.path"))
    }

    static siegeCampGet = 'https://war-service-live.foxholeservices.com/api'
    static generateMilX() {
        def mapList = getApiAsJson(siegeCampGet + "/worldconquest/maps")
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

        mapList.each{ hex ->
            def staticMapItems = getApiAsJson(siegeCampGet + "/worldconquest/maps/$hex/static")
            def mapItems = getApiAsJson(siegeCampGet + "/worldconquest/maps/$hex/dynamic/public")
            def regionId = mapItems.regionId
            mapItems.mapItems.each { mapItem ->
                def MssStringXML = MapIconToMilX.getMilXFromAPI(mapItem, MapRegions.findClosest(staticMapItems, mapItem))
                if(MssStringXML){
                    def x = mapItem.x
                    def y = mapItem.y
                    def latLong = MapRegions.fullConvert(regionId, x, y)

                    DecimalFormat formatter = new DecimalFormat("#.################")
                    latLong.lon = formatter.format(latLong.lon)
                    latLong.lat = formatter.format(latLong.lat)

                    MilXLayerDocument.value()[1].value()[2].appendNode(
                            "MilXGraphic",
                            [:]
                    )
                    def size = MilXLayerDocument.value()[1].value()[2].value().size()
                    MilXLayerDocument.value()[1].value()[2].value()[size-1].appendNode(
                            "MssStringXML",
                            [:],
                            MssStringXML
                    )
                    MilXLayerDocument.value()[1].value()[2].value()[size-1].appendNode(
                            "PointList",
                            [:]
                    )
                    MilXLayerDocument.value()[1].value()[2].value()[size-1].value()[1].appendNode(
                            "Point",
                            [:]
                    )
                    MilXLayerDocument.value()[1].value()[2].value()[size-1].value()[1].value()[0].appendNode(
                            "X",
                            [:],
                            latLong.lon
                    )
                    MilXLayerDocument.value()[1].value()[2].value()[size-1].value()[1].value()[0].appendNode(
                            "Y",
                            [:],
                            latLong.lat
                    )
                }

            }

        }

        return XmlUtil.serialize(MilXLayerDocument)
    }

    static getApiAsJson(url){
        def get = new URL(url).openConnection()
        def getRC = get.getResponseCode()
        def response = new JsonSlurper().parseText(get.getInputStream().getText())
        return response
    }
}
