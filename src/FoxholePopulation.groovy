import static Helper.*

class FoxholePopulation {

    static apiUrl = "https://war-service-live.foxholeservices.com/api"

    static void main(String...args = []){

        def warNum = getApiAsJson('https://war-service-live.foxholeservices.com/api/worldconquest/war').warNumber
        def directoryName = "../War $warNum Population/"
        File directory = new File(directoryName)
        if(!directory.exists()){
            directory.mkdirs()
        }
        File outputCsv = new File("./$directoryName/Foxhole Population Data.csv")
        if(!outputCsv.exists()){
            outputCsv.text = "Timestamp,Warden,Gained,Colonial,Gained,Date\n"
        }

        def runs = 3
        def infiniteMode = args[0]
        def wPrev = null
        def cPrev = null
        while(runs > 0 || infiniteMode){
            def epoch = new Date().getTime().toString()
            def time = new Date().format("dd-MM-yyyy HH:mm:ss").toString()
            def popString = epoch + ","
            def wEnlist = getHomeRegionEnlistments("HomeRegionW")
            popString += wEnlist + ","
            if(wPrev != null){
                popString += wEnlist - cPrev + ","
            } else {
                popString += 0 + ","
            }
            def cEnlist = getHomeRegionEnlistments("HomeRegionC")
            popString += cEnlist + ","
            if(cPrev != null){
                popString += cEnlist - cPrev + ","
            } else {
                popString += 0 + ","
            }
            popString += time + "\n"

            outputCsv.text += popString
            println(popString)
            wPrev = wEnlist
            cPrev = cEnlist
            if(runs > 0){
                runs--
            }
            sleep(10000)
        }

    }



    static getHomeRegionEnlistments(String regionName){
        def enlistments
        def retries = 0
        def exceptions = []
        while(retries++ <= 100){
           try{
               enlistments = getApiAsJson("$apiUrl/worldconquest/warReport/$regionName").totalEnlistments
               return enlistments
           } catch(e){
               exceptions << e
               println(e)
               sleep(1000)
           }
        }

    }

}
