import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder
import groovy.xml.XmlParser

class toMilX {

    static void main(String... args){
        generateMilX()
    }

    static siegeCampGet = 'https://war-service-live.foxholeservices.com/api'
    static generateMilX() {
        def mapList = getApiAsJson(siegeCampGet + "/worldconquest/maps")
        def parser = new XmlParser()
        def writer  = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.MilXLayerDocument_Layer(
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

        mapList.each{ hex ->
            def staticMapItems = getApiAsJson(siegeCampGet + "/worldconquest/maps/$hex/static")
            def mapItems = getApiAsJson(siegeCampGet + "/worldconquest/maps/$hex/dynamic/public")
            println(mapItems)
            def regionId = mapItems.regionId
            mapItems.mapItems.each { mapItem ->
                def MssStringXML = MapIconToMilX.getMilXFromAPI(mapItem, MapRegions.findClosest(staticMapItems, mapItem))
                def current = parser.parseText(writer.toString())
                def x = mapItem.x
                def y = mapItem.y
                def latLong = MapRegions.fullConvert(regionId, x, y)

                parser.createNode(
                        current,
                        "MilXGraphic",
                        [
                                MssStringXML:MssStringXML,
                                PointList:[
                                        X:latLong.lon,
                                        Y:latLong.lat
                                ]
                        ]
                )

            }

        }

        return xml
    }

    static getApiAsJson(url){
        def get = new URL(url).openConnection()
        def getRC = get.getResponseCode()
        println(getRC)
        def response = new JsonSlurper().parseText(get.getInputStream().getText())
        return response
    }
}
