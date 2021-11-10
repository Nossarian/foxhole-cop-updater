class coordinatesHelper {
    def worldExtentMin = [x:-109199.999997,y:-94499.99999580906968410989]
    def worldExtentMax = [x:109199.999997, y:94499.99999580906968410989]
    def normalizedMin = [x: 0, y: 0]
    def normalizedMax = [x:1, y:1]
    def metersMin = [x:0, y:0]
    def metersMax = [x:12000, y:-13500]
    def latLongMin = [x:0, y:0]
    def latLongMax = [x:,y:]

    public latLongToMeters (lat1, lon1, lat2, lon2){
        def R = 6378.137 //radius of earth in KM
        def dLat = (lat2*Math.PI/180) - (lat1*Math.PI/180)
        def dLon = (lon2*Math.PI/180) - (lon1*Math.PI/180)
        def a = (Math.sin(dLat/2)*Math.sin(dLat/2)) + (Math.cos(lat1*Math.PI/180)*Math.cos(lat1*Math.PI/180)*)
    }
}

//region coord -> normalized coord
//normalized -> world unit
//world unit -> meters
//meters -> lat long