import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder
import groovy.xml.XmlParser
import java.lang.reflect.Array

class MapRegions {
    def siegeCampGet = 'https://war-service-live.foxholeservices.com/api'

    static mapBounds = [[0, 0], [12000, 13500]]
    static mapHeight = mapBounds[1][0] - mapBounds[0][0]
    static mapWidth = mapBounds[1][1] - mapBounds[0][1]
    static mapOrigin = [x: mapBounds[1][0] / 2, y: (-1) * mapBounds[1][1] / 2]

    static o = mapOrigin // Shortened
    static w = (mapWidth / 6.06) //Standard Region Width
    static k = (w * Math.sqrt(3) / 2) //Standard Region Height
    static regions = [
            [
                id: 3,
                name: "Deadlands",
                center:
                [o.y, o.x]
            ],
            [
                id: 4,
                name: "Callahans Passage",
                center:
                [o.y + k, o.x]
            ],
            [
                id: 5,
                name: "Marban Hollow",
                center:
                [o.y + 0.5 * k, o.x + 0.75 * w]
            ],
            [
                id: 6,
                name: "Umbral Wildwood",
                center:
                [o.y - k, o.x]
            ],
            [
                id: 7,
                name: "The Moors",
                center:
                [o.y + 1.5 * k, o.x - 0.75 * w]
            ],
            [
                id: 8,
                name: "The Heartlands",
                center:
                [o.y - 1.5 * k, o.x - 0.75 * w]
            ],
            [
                id: 9,
                name: "Loch Mór",
                center:
                [o.y - 0.5 * k, o.x - 0.75 * w]
            ],
            [
                id: 10,
                name: "The Linn of Mercy",
                center:
                [o.y + 0.5 * k, o.x - 0.75 * w]
            ],
            [
                id: 11,
                name: "Reaching Trail",
                center:
                [o.y + 2 * k, o.x]
            ],
            [
                id: 12,
                name: "Stonecradle",
                center:
                [o.y + k, o.x - 1.5 * w]
            ],
            [
                id: 13,
                name: "Farranac Coast",
                center:
                [o.y, o.x - 1.5 * w]
            ],
            [
                id: 14,
                name: "Westgate",
                center:
                [o.y - k, o.x - 1.5 * w]
            ],
            [
                id: 15,
                name: "Fisherman's Row",
                center:
                [o.y - 0.5 * k, o.x - 2.25 * w]
            ],
            [
                id: 16,
                name: "The Oarbreaker Isles",
                center:
                [o.y + 0.5 * k, o.x - 2.25 * w]
            ],
            [
                id: 17,
                name: "Great March",
                center:
                [o.y - 2 * k, o.x]
            ],
            [
                id: 18,
                name: "Tempest Island",
                center:
                [o.y - 0.5 * k, o.x + 2.25 * w]
            ],
            [
                id: 19,
                name: "Godcrofts",
                center:
                [o.y + 0.5 * k, o.x + 2.25 * w]
            ],
            [
                id: 20,
                name: "Endless Shore",
                center:
                [o.y, o.x + 1.5 * w]
            ],
            [
                id: 21,
                name: "Allod's Bight",
                center:
                [o.y - k, o.x + 1.5 * w]
            ],
            [
                id: 22,
                name: "Weathered Expanse",
                center:
                [o.y + k, o.x + 1.5 * w]
            ],
            [
                id: 23,
                name: "The Drowned Vale",
                center:
                [o.y - 0.5 * k, o.x + 0.75 * w]
            ],
            [
                id: 24,
                name: "Shackled Chasm",
                center:
                [o.y - 1.5 * k, o.x + 0.75 * w]
            ],
            [
                id: 25,
                name: "Viper Pit",
                center:
                [o.y + 1.5 * k, o.x + 0.75 * w]
            ],
            [
                id: 29,
                name: "Nevish Line",
                center:
                [o.y + 1.5 * k, o.x - 2.25 * w]
            ],
            [
                id: 30,
                name: "Acrithia",
                center:
                [o.y - 2.5 * k, o.x + 0.75 * w]
            ],
            [
                id: 31,
                name: "Red River",
                center:
                [o.y - 2.5 * k, o.x - 0.75 * w]
            ],
            [
                id: 32,
                name: "Callum's Cape",
                center:
                [o.y + 2 * k, o.x - 1.5 * w]
            ],
            [
                id: 33,
                name: "Speaking Woods",
                center:
                [o.y + 2.5 * k, o.x - 0.75 * w]
            ],
            [
                id: 34,
                name: "Basin Sionnach",
                center:
                [o.y + 3 * k, o.x]
            ],
            [
                id: 35,
                name: "Howl County",
                center:
                [o.y + 2.5 * k, o.x + 0.75 * w]
            ],
            [
                id: 36,
                name: "Clanshead Valley",
                center:
                [o.y + 2 * k, o.x + 1.5 * w]
            ],
            [
                id: 37,
                name: "Morgens Crossing",
                center:
                [o.y + 1.5 * k, o.x + 2.25 * w]
            ],
            [
                id: 38,
                name: "The Fingers",
                center:
                [o.y - 1.5 * k, o.x + 2.25 * w]
            ],
            [
                id: 39,
                name: "Terminus",
                center:
                [o.y - 2 * k, o.x + 1.5 * w]
            ],
            [
                id: 40,
                name: "Kalokai",
                center:
                [o.y - 3 * k, o.x]
            ],
            [
                id: 41,
                name: "Ash Fields",
                center:
                [o.y - 2 * k, o.x - 1.5 * w]
            ],
            [
                id: 42,
                name: "Origin",
                center:
                [o.y - 1.5 * k, o.x - 2.25 * w]
            ]
    ]
    static convertCoords(regionID, x, y){
        def region =  this.regions.find{it ->
            it.id == regionID
        }
        println(region.name)
        def xcoord = region.center[1] - (w/2) + (w*x)
        def ycoord = region.center[0] + (k/w) - (k*y)
        return [xcoord, ycoord]
    }

    static meteresToLatLong (mx, my){
        def lat = my/111320
        def lon = mx/(40075*Math.cos(lat)/360)
        return [x:lon, y:lat]
    }

    static fullConvert (regionID, x, y){
        def worldCoords = convertCoords(regionID, x, y)
        return meteresToLatLong(worldCoords[0], worldCoords[1])
    }


    static findClosest (staticMapItems, mapItem) {
        ArrayList<Map> closestNames = [:]

        staticMapItems.mapTextItems.each{mapTextItem ->
            def xdif = Math.abs(mapItem.x - mapTextItem.x)
            def ydif = Math.abs(mapItem.y - mapTextItem.y)
            def distance = Math.sqrt(Math.pow(xdif, 2) + Math.pow(ydif,2))
            closestNames.add([text: mapTextItem.text, distance: distance])

        }
        def closestName = closestNames[0]
        closestNames.each{ it ->
            if (it.distance <= closestName.distance){
                closestName = it
            }
        }
        return closestName.text
    }

    private generateMilX() {
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
            def regionId = mapItems.regionId
            mapItems.each { mapItem ->
                def MssStringXML = MapIconToMilX.getMilXFromAPI(mapItem, findClosest(staticMapItems, mapItem))
                def current = parser.parseText(writer.toString())
                def x = mapItem.x
                def y = mapItem.y
                def latLong = fullConvert(regionId, x, y)

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

    def getApiAsJson(url){
        def get = new URL(url).openConnection()
        def getRC = get.getResponseCode()
        println(getRC)
        def response = new JsonSlurper().parseText(get.getInputStream().getText())
        return response
    }
    abstract class Region {
        Integer regionId
        String name
        Array center
    }

    class MapTextItem{
        def constructor(regionID, text, x, y){
            regionID = regionID
            text = text
            x = x
            y = y

        }
    }

}


