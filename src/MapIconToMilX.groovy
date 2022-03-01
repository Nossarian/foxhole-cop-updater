/**
 *
 */
class MapIconToMilX {
    static factionCheck(mapItem, factions = true){
        def result = [
                faction: "N",
                color: "\"\$0000FE00\""
        ]
        if(factions){
            if(mapItem.teamId == "WARDENS"){
                result.faction = "F"
                result.color = "\"\$00C07000\""
            }
            if(mapItem.teamId == "COLONIALS"){
                result.faction = "H"
                result.color = "\"clRed\""
            }
        }
        return result
    }
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
    static getTextLabels(staticMapItem){
        def type = staticMapItem.mapMarkerType
        if (type == "Minor"){
            return "<Symbol ID=\"GF9PT----------\"><Attribute ID=\"XM\"><Line Style=\"psClear\"/><Fill Transparency=\"100\"/>" +
                    "<Text Color=\"clBlack\"/></Attribute><Attribute ID=\"XN\">{\\rtf1\\deff0{\\fonttbl{\\f0 Times New Roman;}}" +
                    "\\pard\\plain\\qc{\\fs16\\cf0 $staticMapItem.text}\\par}</Attribute></Symbol>"
        }
//        if(type == "Major"){
//            return "<Symbol ID=\"GF9PT----------\"><Attribute ID=\"XM\"><Line Style=\"psClear\"/><Fill Transparency=\"100\"/>" +
//                    "<Text Color=\"clBlack\"/></Attribute><Attribute ID=\"XN\">{\\rtf1\\deff0{\\fonttbl{\\f0 Times New Roman;}}" +
//                    "\\pard\\plain\\qc{\\cf0 $staticMapItem.text}\\par}</Attribute></Symbol>"
//        }
    }
    static getRdzExceptionSymbolsFromAPI(mapItem, rdzException = false){
        def fc = factionCheck(mapItem, true)
        def fac = fc.faction
        def col = fc.color
        if([20,21,23,32,33,35,38,40,41,45,46,47,56,57,58].contains(mapItem.iconType)){
            def transparency = "\"100\""
            def lineStyle = "\"psDashDotDot\""
            return "<Symbol ID=\"G$fac"+"9PAC---------\"><Attribute ID=\"XM\"><Line Style=$lineStyle Color=$col/><Fill Color=$col Transparency=$transparency/></Attribute></Symbol>"
        }
    }
    static getAiRangesFromAPI(mapItem, factions = true){
        def fc = factionCheck(mapItem, factions)
        def fac = fc.faction
        def col = fc.color
        def s = flagScorchedCheck(mapItem.flags)
        if([35,45,46,47, 53, 56,57,58].contains(mapItem.iconType)){
            def fillStyle = "bsSolidGS"
            def transparency = "90"
            if (mapItem.iconType == 53){
                fillStyle = "bsDottedGS"
                transparency = "50"
            }
            return "<Symbol ID=\"G$fac"+"9$s"+"AC---------\">" +
                    "<Attribute ID=\"XM\"><Line Width=\"20\" Color=$col/><Fill Color=$col StyleEx=\"$fillStyle\" Transparency=\"$transparency\"/><Text Color=$col/></Attribute>" +
                    "</Symbol>"
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
    static getMegaStructureRangesFromAPI(mapItem, structure, factions =  true){
        def fc = factionCheck(mapItem, factions)
        def fac = fc.faction
        def structID = -1
        if (structure == "SC"){
            structID = 59
        }
        if (structure == "IC"){
            structID = 60
        }
        if(mapItem.iconType ==  structID){
        return "<Symbol ID=\"G$fac"+"MPNM---------\"/>"
        }
    }
    static getMilXFromAPI(mapItem, nearestText, factions = true){
        def fc = factionCheck(mapItem, factions)
        def fac = fc.faction
        def col = fc.color
        if(nearestText == "Gravekeeper's Holdfast"){
            nearestText = "Gravekeeper's Hldfst"
        }
        def s = flagScorchedCheck(mapItem.flags)
        if(mapItem.iconType == 11){ //Hospital
            return "<Symbol ID=\"S$fac"+"G$s"+"IXH---H----\"/>"
        }
        if(mapItem.iconType == 12){ //Vehicle Factory/Garage
            return "<Symbol ID=\"S$fac"+"G$s"+"IMV---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
        }
        if(mapItem.iconType == 17){ //Refinery
            return "<Symbol ID=\"S$fac"+"G$s"+"IP----H----\"/>"
        }
        if(mapItem.iconType == 18){ //Shipyard
            return "<Symbol ID=\"S$fac"+"G$s"+"IMS---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
        }
        if(mapItem.iconType == 19){ //Engineering Center
            return "<Symbol ID=\"S$fac"+"G$s"+"IUR---H----\"/>"
        }
        if(mapItem.iconType == 27){ //Keep
            return "<Symbol ID=\"S$fac"+"G$s"+"I-----H---G\"><Attribute ID=\"AA\">CP</Attribute><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"XD\">I</Attribute></Symbol>"
        }
        if(mapItem.iconType == 28){ //Observation Tower
            return "<Symbol ID=\"G$fac"+"G$s"+"DPOS-------\"/>"
        }
        if(mapItem.iconType == 33){ //Supply Depot
            return "<Symbol ID=\"G$fac"+"S$s"+"PSZ-------G\"/>"
        }
        if(mapItem.iconType == 34){ //Factory
            return "<Symbol ID=\"S$fac"+"G$s"+"IMG---H----\"/>"
        }
        if(mapItem.iconType == 35){ //Safe House
            return "<Symbol ID=\"O$fac"+"I$s"+"S----------\"/>"
        }
        if(mapItem.iconType == 37){ //Rocket Silo
            return "<Symbol ID=\"S$fac"+"A$s"+"WMB--------\"/>"
        }
        if(mapItem.iconType == 39) { //Construction Yard
            return "<Symbol ID=\"S$fac"+"G$s"+"IME---H----\"/>"
        }
        if(mapItem.iconType == 45){ //Relic Base 1
            return "<Symbol ID=\"S$fac"+"G$s"+"U------E--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
        }
        if(mapItem.iconType == 46){ //Relic Base 2
            return "<Symbol ID=\"S$fac"+"G$s"+"U------F--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
        }
        if(mapItem.iconType == 47){ //Relic Base 3
            return "<Symbol ID=\"S$fac"+"G$s"+"U------G--G\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
        }
        if(mapItem.iconType == 51){ //Mass Production Factory
            return "<Symbol ID=\"S$fac"+"G$s"+"IMN---H----\"><Attribute ID=\"XD\">F</Attribute></Symbol>"
        }
        if(mapItem.iconType == 52){ //Seaport
            return "<Symbol ID=\"G$fac"+"S$s"+"PSZ-------G\"/>"
        }
        if(mapItem.iconType == 53){ //Coastal Gun
            return "<Symbol ID=\"S$fac"+"G$s"+"EWD--------\"/>"
        }
        if(mapItem.iconType == 56){ //Town Hall 1
            if(mapItem.flags == 41){
                return "<Symbol ID=\"E$fac"+"F$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            } else {
                return "<Symbol ID=\"S$fac"+"G$s"+"U------B--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 57){ //Town Hall 2
            if(mapItem.flags == 41){
                return "<Symbol ID=\"E$fac"+"F$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            } else {
                return "<Symbol ID=\"S$fac"+"G$s"+"U------C--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 58){ //Town Hall 3
            if(mapItem.flags == 41){
                return "<Symbol ID=\"E$fac"+"F$s"+"F----------\"><Attribute ID=\"T\">$nearestText</Attribute></Symbol>"
            } else {
                return "<Symbol ID=\"S$fac"+"G$s"+"U------D--G\"><Attribute ID=\"T\">$nearestText</Attribute><Attribute ID=\"AA\">GOV</Attribute></Symbol>"
            }
        }
        if(mapItem.iconType == 59){ //Storm Cannon
            return "<Symbol ID=\"S$fac"+"G$s"+"IMM---H----\"/>"
        }
        if(mapItem.iconType == 60){ //Intel Center
            return "<Symbol ID=\"S$fac"+"GPUCFTR------\"/>"
        }
    }
}
