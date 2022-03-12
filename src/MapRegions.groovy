
class MapRegions {

    static hexSideLength = 1097.0
    static hexHeight = hexSideLength*Math.sqrt(3)
    static hexWidth = hexSideLength*2
    static mapHeight = hexHeight*7
    static mapWidth = hexSideLength*11
//    static mapHeight = 13300.0
//    static mapWidth = 12067.0
    static mapOrigin = [x: mapWidth/2.0 , y: (-1*mapHeight/2.0) + hexHeight/2.0]
//    static mapOrigin = [x:0,y:hexHeight/2,]
    static o = mapOrigin // Shortened
    static k = hexHeight
    static w = hexWidth
//    static w = (mapWidth / 6.06) //Standard Region Width
//    static k = w*Math.sqrt(3)/2 //Standard Region Height
//    static k = 1900.0
//    static w = 2194.0
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
        def xcoord = region.center[1] - (w/2) + (w*x)
        def ycoord = region.center[0] + (k/w) - (k*y)
        return [xcoord, ycoord]
    }

    static meteresToLatLong (mx, my){
        def lat = my/111111.11111111
        def lon = mx/(40075017.0/360)*Math.cos(lat*Math.PI/180)
//        def lon = mx/111111.111111*Math.cos(lat)
        return [lon:lon, lat:lat]
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

}


