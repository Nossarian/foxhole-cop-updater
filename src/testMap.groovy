import groovy.json.JsonSlurper

class testMap {
    static void main(String[] args){
        def get = new URL("https://war-service-live.foxholeservices.com/api/worldconquest/maps").openConnection()
        def getRC = get.getResponseCode()
        println(getRC)
        def response = new JsonSlurper().parseText(get.getInputStream().getText())
        println(getRC)
//        if (getRC.equals(200)) {
//            println(get.getInputStream().getText())
//        }

    }
}
