class MapIconToMilX {
    static getMilXFromAPI(mapItem, nearestText){
        def includeResources = true
        if(mapItem.iconType == 11){ //Hospital
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIX---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIX---H----\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIX---H----\"/>"
            }

        }
        if(mapItem.iconType == 12){ //Vehicle Factory/Garage
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 17){ //Refinery
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIP----H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIP----H----\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIP----H----\"/>"
            }
        }
        if(mapItem.iconType == 18){ //Shipyard
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 19){ //Engineering Center
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIUR---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIUR---H----\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIUR---H----\"/>"
            }
        }
        if(mapItem.iconType == 20 && includeResources){ //Salvage Field
            return "<Symbol ID=\"SUGPIRM---H----\"/>"
        }
        if(mapItem.iconType == 21 && includeResources){ //Component Field
            return "<Symbol ID=\"SUGPIRM---H----\"/>"
        }
        if(mapItem.iconType == 23 && includeResources){ //Sulfur Field
            return "<Symbol ID=\"SUGPIRM---H----\"/>"
        }
        if(mapItem.iconType == 28){ //Observation Tower
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"GFGPDPOS-------\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"GHGPDPOS-------\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"GNGPDPOS-------\"/>"
            }
        }
        if(mapItem.iconType == 32 && includeResources){ //Sulfur Mine
            return "<Symbol ID=\"SUGPIRM---H----\"/>"
        }
        if(mapItem.iconType == 33){ //Supply Depot
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"GFSPPSZ-------G\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"GHSPPSZ-------G\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"GNSPPSZ-------G\"/>"
            }
        }
        if(mapItem.iconType == 35){ //Safe House
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"OFI-S----------\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"OHI-S----------\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"ONI-S----------\"/>"
            }
        }
        if(mapItem.iconType == 37){ //Rocket Silo
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SNA-WMB--------\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SNA-WMB--------\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNA-WMB--------\"/>"
            }
        }
        if(mapItem.iconType == 38 && includeResources){ //Salvage Mine
            return "<Symbol ID=\"SUGPIRM---H----\"/>"
        }
        if(mapItem.iconType == 39){ //Construction Yard
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIME---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIME---H----\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIME---H----\"/>"
            }
        }
        if(mapItem.iconType == 40 && includeResources){ //Component Mine
            return "<Symbol ID=\"SUGPIRM---H----\"/>"
        }
        if(mapItem.iconType == 41 && includeResources){ //Oil Well
            return "<Symbol ID=\"SUGPIRM---H----\"/>"
        }
        if(mapItem.iconType == 45){ //Relic Base 1
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 46){ //Relic Base 2
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 2
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 2
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 2
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 51){ //Mass Production Factory
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 52){ //Seaport
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"GFSPPSZ-------G\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"GHSPPSZ-------G\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"GNSPPSZ-------G\"/>"
            }
        }
        if(mapItem.iconType == 53){ //Coastal Gun
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPEWD--------\"/>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPEWD--------\"/>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPEWD--------\"/>"
            }
        }
        if(mapItem.iconType == 56){ //Town Hall 1
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 57){ //Town Hall 2
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 58){ //Town Hall 3
            if(mapItem.teamId == "WARDENS"){
                return "<Symbol ID=\"SFGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS"){
                return "<Symbol ID=\"SHGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "NONE"){
                return "<Symbol ID=\"SNGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
    }
}
