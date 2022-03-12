import static Helper.*

class FoxholePopulation {

    static apiUrl = "https://war-service-live.foxholeservices.com/api"

    static void main(String...args = []){

        def warNum = getApiAsJson('https://war-service-live.foxholeservices.com/api/worldconquest/war').warNumber
        def timestamp = new Date().format("dd-MM-yyyy HH.mm.ss")
        def directoryName = "../War $warNum Population $timestamp/"
        File directory = new File(directoryName)
        if(!directory.exists()){
            directory.mkdirs()
        }
        File outputCsv = new File("./$directoryName/Foxhole Population Data.csv")
        outputCsv.text = "Timestamp,Warden,Gained,Colonial,Gained,Date\n"

        def runs = 60000
        def wPrev = 0
        def cPrev = 0
        while(runs > 0){
            def epoch = new Date().getTime().toString()
            def time = new Date().format("dd-MM-yyyy HH:mm:ss").toString()
            def popString = epoch + ","
            def wEnlist = getHomeRegionEnlistments("HomeRegionW")
            popString += wEnlist + ","
            popString += wEnlist - wPrev + ","
            def cEnlist = getHomeRegionEnlistments("HomeRegionC")
            popString += cEnlist + ","
            popString += cEnlist - cPrev + ","
            popString += time + "\n"

            outputCsv.text += popString
            println(popString)
            wPrev = wEnlist
            cPrev = cEnlist
            runs--
            sleep(10000)
        }

    }



    static getHomeRegionEnlistments(String regionName){
        def enlistments = getApiAsJson("$apiUrl/worldconquest/warReport/$regionName").totalEnlistments
        return enlistments
    }

}
