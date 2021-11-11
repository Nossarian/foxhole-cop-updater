class MapIconToMilX {
    static getMilXFromAPI(mapItem, nearestText){
        if(mapItem.iconType == 11){ //Hospital
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIX---H----\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIX---H----\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNGPIX---H----\"/&gt;"
            }

        }
        if(mapItem.iconType == 12){ //Vehicle Factory/Garage
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIMV---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIMV---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNGPIMV---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
        }
        if(mapItem.iconType == 33){ //Supply Depot
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"GFSPPSZ-------G\"/&gt"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"GHSPPSZ-------G\"/&gt"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"GNSPPSZ-------G\"/&gt"
            }
        }
        if(mapItem.iconType == 18){ //Shipyard
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIMS---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIMS---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNGPIMS---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
        }
        if(mapItem.iconType == 17){ //Refinery
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIP----H----\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIP----H----\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNGPIP----H----\"/&gt;"
            }
        }
        if(mapItem.iconType == 19){ //Engineering Center
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIUR---H----\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIUR---H----\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNGPIUR---H----\"/&gt;"
            }
        }
        if(mapItem.iconType == 35){ //Safe House
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"OFI-S----------\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"OHI-S----------\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"ONI-S----------\"/&gt;"
            }
        }
        if(mapItem.iconType == 52){ //Seaport
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"GFSPPSZ-------G\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"GHSPPSZ-------G\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"GNSPPSZ-------G\"/&gt;"
            }
        }
        if(mapItem.iconType == 53){ //Coastal Gun /*TODO*/
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPEWA--------\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPEWA--------\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNGPEWA--------\"/&gt;"
            }
        }
        if(mapItem.iconType == 56){ //Town Hall 1
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIG----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIG----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return
            }
        }
        if(mapItem.iconType == 57){ //Town Hall 2
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIG----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIG----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return
            }
        }
        if(mapItem.iconType == 58){ //Town Hall 3
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIG----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIG----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return
            }
        }
        if(mapItem.iconType == 45){ //Relic Base 1
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIM----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIM----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return
            }
        }
        if(mapItem.iconType == 46){ //Relic Base 2
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIM----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIM----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 2
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIM----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIM----H----\"&gt;&lt;Attribute ID=\"T\"&gt;$nearestText&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return
            }
        }
        if(mapItem.iconType == 51){ //Mass Production Factory
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFGPIMN---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHGPIMN---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNGPIMN---H----\"&gt;&lt;Attribute ID=\"XD\"&gt;F&lt;/Attribute&gt;&lt;/Symbol&gt;"
            }
        }
        if(mapItem.iconType == 28){ //Observation Tower
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"GFGPDPOS-------\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"GHGPDPOS-------\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"GNGPDPOS-------\"/&gt;"
            }
        }
        if(mapItem.iconType == 37){ //Rocket Silo
            if(mapItem.teamId == "WARDENS"){
                return "&lt;Symbol ID=\"SFA-WMB--------\"/&gt;"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "&lt;Symbol ID=\"SHA-WMB--------\"/&gt;"
            }
            if(mapItem.teamId == "NONE"){
                return "&lt;Symbol ID=\"SNA-WMB--------\"/&gt;"
            }
        }

    }
}
