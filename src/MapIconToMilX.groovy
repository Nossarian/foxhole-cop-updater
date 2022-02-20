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

    static getBasePins(mapItem, nearestText){
        if(nearestText == "Gravekeeper's Holdfast"){
            nearestText = "Gravekeeper's Hldfst"
        }
        if([45,46,47,56,57,58].contains(mapItem.iconType)){
            return "<Symbol ID=\"GF9PP----------\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XM\"><Fill Color=\"\$00FEFF00\"/></Attribute></Symbol>"
        }
    }

    static getRdzExceptionSymbolsFromAPI(mapItem, rdzException = false){
        def faction = "N"
        def lineColor = "\"\$0000FE00\""
        if (mapItem.teamId == "WARDENS"){
            lineColor = "\"\$00C07000\""
            faction = "F"
        }
        if (mapItem.teamId == "COLONIALS"){
            lineColor = "\"clRed\""
            faction = "H"
        }
        if([20,21,23,32,33,35,38,40,41,45,46,47,56,57,58].contains(mapItem.iconType)){
            def transparency = "\"100\""
            def lineStyle = "\"psDashDotDot\""

            return "<Symbol ID=\"G$faction"+"9PAC---------\"><Attribute ID=\"XM\"><Line Style=$lineStyle Color=$lineColor/><Fill Color=$lineColor Transparency=$transparency/></Attribute></Symbol>"
        }

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
            returnArray.add("<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">Su</Attribute></Symbol>")
        }
        if(mapItem.iconType == 38){ //Salvage Mine
            returnArray.add("<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">Sa</Attribute></Symbol>")
        }
        if(mapItem.iconType == 40){ //Component Mine
            returnArray.add("<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">C</Attribute></Symbol>")
        }

        if(mapItem.iconType == 41){ //Oil Well
            returnArray.add("<Symbol ID=\"SUG$s"+"U-----H---G\"><Attribute ID=\"AA\">Oil</Attribute></Symbol>")
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
        if(nearestText == "Gravekeeper's Holdfast"){
            nearestText = "Gravekeeper's Hldfst"
        }
        def s = flagScorchedCheck(mapItem.flags)
        if(mapItem.iconType == 11){ //Hospital
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IXH---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IXH---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IXH---H----\"/>"
            }

        }
        if(mapItem.iconType == 12){ //Vehicle Factory/Garage
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 17){ //Refinery
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IP----H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IP----H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IP----H----\"/>"
            }
        }
        if(mapItem.iconType == 18){ //Shipyard
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 19){ //Engineering Center
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IUR---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IUR---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IUR---H----\"/>"
            }
        }
        if(mapItem.iconType == 27){ //Keep
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"I-----H---G\"><Attribute ID=\"AA\">CP</Attribute><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XD\">I</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"I-----H---G\"><Attribute ID=\"AA\">CP</Attribute><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XD\">I</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"I-----H---G\"><Attribute ID=\"AA\">CP</Attribute><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XD\">I</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 28){ //Observation Tower
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GFG$s"+"DPOS-------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GHG$s"+"DPOS-------\"/>"
            }
            else{
                return "<Symbol ID=\"GNG$s"+"DPOS-------\"/>"
            }
        }
        if(mapItem.iconType == 33){ //Supply Depot
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GFS$s"+"PSZ-------G\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GHS$s"+"PSZ-------G\"/>"
            }
            else{
                return "<Symbol ID=\"GNS$s"+"PSZ-------G\"/>"
            }
        }
        if(mapItem.iconType == 34){ //Factory
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IMG---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IMG---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IMG---H----\"/>"
            }
        }
        if(mapItem.iconType == 35){ //Safe House
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"OFI$s"+"S----------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"OHI$s"+"S----------\"/>"
            }
            else{
                return "<Symbol ID=\"ONI$s"+"S----------\"/>"
            }
        }
        if(mapItem.iconType == 37){ //Rocket Silo
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFA$s"+"WMB--------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHA$s"+"WMB--------\"/>"
            }
            else{
                return "<Symbol ID=\"SNA$s"+"WMB--------\"/>"
            }
        }
        if(mapItem.iconType == 39) { //Construction Yard
            if (mapItem.teamId == "WARDENS" && factions) {
                return "<Symbol ID=\"SFG$s"+"IME---H----\"/>"
            }
            if (mapItem.teamId == "COLONIALS" && factions) {
                return "<Symbol ID=\"SHG$s"+"IME---H----\"/>"
            } else {
                return "<Symbol ID=\"SNG$s"+"IME---H----\"/>"
            }
        }
        if(mapItem.iconType == 45){ //Relic Base 1
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"U------E--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"U------E--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"U------E--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 46){ //Relic Base 2
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"U------F--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"U------F--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"U------F--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 47){ //Relic Base 3
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"U------G--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"U------G--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"U------G--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 51){ //Mass Production Factory
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 52){ //Seaport
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"GFS$s"+"PSZ-------G\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"GHS$s"+"PSZ-------G\"/>"
            }
            else{
                return "<Symbol ID=\"GNS$s"+"PSZ-------G\"/>"
            }
        }
        if(mapItem.iconType == 53){ //Coastal Gun
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"EWD--------\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"EWD--------\"/>"
            }
            else {
                return "<Symbol ID=\"SNG$s"+"EWD--------\"/>"
            }
        }
        if(nearestText == "The Jade Cove"){
            println(nearestText)
        }
        if(mapItem.iconType == 56){ //Town Hall 1
            if(mapItem.teamId == "WARDENS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EFF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SFG$s"+"U------B--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                }
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EHF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SHG$s"+"U------B--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                }
            }
            else{
                if(mapItem.flags == 41 || mapItem.flags == 17){
                    return "<Symbol ID=\"ENF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SNG$s"+"U------B--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                }
            }
        }
        if(mapItem.iconType == 57){ //Town Hall 2
            if(mapItem.teamId == "WARDENS" && factions){
                if(mapItem.teamId == "WARDENS" && factions){
                    if(mapItem.flags == 41){
                        return "<Symbol ID=\"EFF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    } else {
                        return "<Symbol ID=\"SFG$s"+"U------C--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                    }
                }
                if(mapItem.teamId == "COLONIALS" && factions){
                    if(mapItem.flags == 41){
                        return "<Symbol ID=\"EHF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    } else {
                        return "<Symbol ID=\"SHG$s"+"U------C--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                    }
                }
                else{
                    if(mapItem.flags == 41 || mapItem.flags == 17){
                        return "<Symbol ID=\"ENF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                    } else {
                        return "<Symbol ID=\"SNG$s"+"U------C--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                    }
                }
            }
        }
        if(mapItem.iconType == 58){ //Town Hall 3
            if(mapItem.teamId == "WARDENS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EFF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SFG$s"+"U------D--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                }
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                if(mapItem.flags == 41){
                    return "<Symbol ID=\"EHF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SHG$s"+"U------D--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                }
            }
            else{
                if(mapItem.flags == 41 || mapItem.flags == 17){
                    return "<Symbol ID=\"ENF$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
                } else {
                    return "<Symbol ID=\"SNG$s"+"U------D--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
                }
            }
        }
        if(mapItem.iconType == 59){ //Storm Cannon
            if(mapItem.teamId == "WARDENS" && factions){
                return "<Symbol ID=\"SFG$s"+"IMM---H----\"/>"
            }
            if(mapItem.teamId == "COLONIALS" && factions){
                return "<Symbol ID=\"SHG$s"+"IMM---H----\"/>"
            }
            else{
                return "<Symbol ID=\"SNG$s"+"IMM---H----\"/>"
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
