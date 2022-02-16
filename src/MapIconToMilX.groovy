import groovy.json.StringEscapeUtils

/**
 *
 */

class MapIconToMilX {

    static flagScorchedCheck (flag){
        def symbolStatus = "P"
        if (flag == 16 || flag == 17){
            symbolStatus = "X"
        }
        return symbolStatus
    }

    static getAiRangesFromAPI(mapItem, factions = true){
        def s = flagScorchedCheck(mapItem.flags)
        if([35,45,46,47, 53, 56,57,58].contains(mapItem.iconType)){
            def fillStyle = "bsSolidGS"
            def transparency = "90"
            if (mapItem.iconType == 53){
                fillStyle = "bsDottedGS"
                transparency = "50"
            }
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GF9$s"+"AC---------\">" +
                        "<Attribute ID=\"XM\"><Line Width=\"20\" Color=\"\$00C07000\"/><Fill Color=\"\$0C07000\" StyleEx=\"$fillStyle\" Transparency=\"$transparency\"/><Text Color=\"\$0C07000\"/></Attribute>" +
                        "</Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GF9$s"+"AC---------\">" +
                        "<Attribute ID=\"XM\"><Line Width=\"20\" Color=\"clRed\"/><Fill Color=\"clRed\" StyleEx=\"$fillStyle\" Transparency=\"$transparency\"/><Text Color=\"clRed\"/></Attribute>" +
                        "</Symbol>"
            }
            else{
                return "<Symbol ID=\"GF9$s"+"AC---------\">" +
                        "<Attribute ID=\"XM\"><Line Width=\"20\" Color=\"\$0000FE00\"/><Fill Color=\"\$0000FE00\" StyleEx=\"$fillStyle\" Transparency=\"$transparency\"/><Text Color=\"\$0000FE00\"/></Attribute>" +
                        "</Symbol>"
            }
        }
    }

    static getResourceNodeNoBuild(mapItem){
        def s = flagScorchedCheck(mapItem.flags)
        def returnArray = []
        if(mapItem.iconType == 20){ //Salvage Field
            returnArray.add("<Symbol ID=\"SUG$s"+"U---------G\"><Attribute ID=\"AA\">Sa</Attribute></Symbol>")
        }
        if(mapItem.iconType == 21){ //Component Field
            returnArray.add("<Symbol ID=\"SUG$s"+"U---------G\"><Attribute ID=\"AA\">C</Attribute></Symbol>")
        }
        if(mapItem.iconType == 23){ //Sulfur Field
            returnArray.add("<Symbol ID=\"SUG$s"+"U---------G\"><Attribute ID=\"AA\">Su</Attribute></Symbol>")
        }
        if(mapItem.iconType == 32){ //Sulfur Mine
            return "<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">Su</Attribute></Symbol>"
        }
        if(mapItem.iconType == 38){ //Salvage Mine
            return "<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">Sa</Attribute></Symbol>"
        }
        if(mapItem.iconType == 40){ //Component Mine
            return "<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">C</Attribute></Symbol>"
        }

        if(mapItem.iconType == 41){ //Oil Well
            return "<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">Oil</Attribute></Symbol>"
        }
        if([20,21,23].contains(mapItem.iconType)){
            returnArray.add( "<Symbol ID=\"GF9PAC---------\">" +
                    "<Attribute ID=\"XM\"><Line Width=\"20\" Color=\"\$0000FE00\"/><Fill Color=\"\$0000FE00\" StyleEx=\"bsSolidGS\" Transparency=\"90\"/><Text Color=\"\$0000FE00\"/></Attribute>" +
                    "</Symbol>")
        }
        return returnArray
    }

    static getStormCannonRangesFromAPI(mapItem, factions =  true){
        if(mapItem.iconType ==  59){
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GFMPNM---------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GHMPNM---------\"/>"
            }
            else{
                return "<Symbol ID=\"GFMPNM---------\"/>"
            }
        }
    }

    static getMilXFromAPI(mapItem, nearestText, factions = true){
        if(mapItem.iconType == 11){ //Hospital
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIXH---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIXH---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNGPIXH---H----\"/>"
            }

        }
        if(mapItem.iconType == 12){ //Vehicle Factory/Garage
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 17){ //Refinery
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIP----H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIP----H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNGPIP----H----\"/>"
            }
        }
        if(mapItem.iconType == 18){ //Shipyard
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 19){ //Engineering Center
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIUR---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIUR---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNGPIUR---H----\"/>"
            }
        }
        if(mapItem.iconType == 27){ //Keep
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPI-----H---G\"><Attribute ID=\"AA\">CP</Attribute><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XD\">I</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPI-----H---G\"><Attribute ID=\"AA\">CP</Attribute><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XD\">I</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPI-----H---G\"><Attribute ID=\"AA\">CP</Attribute><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XD\">I</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 28){ //Observation Tower
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GFGPDPOS-------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GHGPDPOS-------\"/>"
            }
            else{
                return "<Symbol ID=\"GNGPDPOS-------\"/>"
            }
        }
        if(mapItem.iconType == 33){ //Supply Depot
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GFSPPSZ-------G\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GHSPPSZ-------G\"/>"
            }
            else{
                return "<Symbol ID=\"GNSPPSZ-------G\"/>"
            }
        }
        if(mapItem.iconType == 34){ //Factory
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIMG---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIMG---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNGPIMG---H----\"/>"
            }
        }
        if(mapItem.iconType == 35){ //Safe House
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"OFI-S----------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"OHI-S----------\"/>"
            }
            else{
                return "<Symbol ID=\"ONI-S----------\"/>"
            }
        }
        if(mapItem.iconType == 37){ //Rocket Silo
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFAPWMB--------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHAPWMB--------\"/>"
            }
            else{
                return "<Symbol ID=\"SNAPWMB--------\"/>"
            }
        }
        if(mapItem.iconType == 39) { //Construction Yard
            if (mapItem.teamId == "WARDENS" && factions) {
                return "<Symbol ID=\"SFGPIME---H----\"/>"
            }
            if (mapItem.teamId == "COLONIALS" && factions) {
                return "<Symbol ID=\"SHGPIME---H----\"/>"
            } else {
                return "<Symbol ID=\"SNGPIME---H----\"/>"
            }
        }
        if(mapItem.iconType == 45){ //Relic Base 1
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 46){ //Relic Base 2
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 2
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 2
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 2
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIM----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 51){ //Mass Production Factory
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNGPIMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 52){ //Seaport
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GFSPPSZ-------G\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GHSPPSZ-------G\"/>"
            }
            else{
                return "<Symbol ID=\"GNSPPSZ-------G\"/>"
            }
        }
        if(mapItem.iconType == 53){ //Coastal Gun
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPEWD--------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPEWD--------\"/>"
            }
            else {
                return "<Symbol ID=\"SNGPEWD--------\"/>"
            }
        }
        if(mapItem.iconType == 56){ //Town Hall 1
            if(mapItem.teamId == "WARDENS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EFFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SFGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                }
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EHFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SHGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                }
            }
            else{
                if(mapItem.flags == 41 || mapItem.flags == 17){
                    return "<Symbol ID=\"ENFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SNGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                }
            }
        }
        if(mapItem.iconType == 57){ //Town Hall 2
            if(mapItem.teamId == "WARDENS" && factions){
                if(mapItem.teamId == "WARDENS" && factions){
                    if(mapItem.flags == 41){
                        return "<Symbol ID=\"EFFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    } else {
                        return "<Symbol ID=\"SFGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    }
                }
                if(mapItem.teamId == "COLONIALS" && factions){
                    if(mapItem.flags == 41){
                        return "<Symbol ID=\"EHFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    } else {
                        return "<Symbol ID=\"SHGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    }
                }
                else{
                    if(mapItem.flags == 41 || mapItem.flags == 17){
                        return "<Symbol ID=\"ENFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    } else {
                        return "<Symbol ID=\"SNGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    }
                }
            }
        }
        if(mapItem.iconType == 58){ //Town Hall 3
            if(mapItem.teamId == "WARDENS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EFFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SFGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                }
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EHFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SHGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                }
            }
            else{
                if(mapItem.flags == 41 || mapItem.flags == 17){
                    return "<Symbol ID=\"ENFPF----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SNGPIG----H----\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                }
            }
        }
        if(mapItem.iconType == 59){ //Storm Cannon
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPIMM---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPIMM---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNGPIMM---H----\"/>"
            }
        }
        if(mapItem.iconType == 60){ //Intel Center
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFGPUCFTR------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHGPUCFTR------\"/>"
            }
            else{
                return "<Symbol ID=\"SNGPUCFTR------\"/>"
            }
        }
    }
}
