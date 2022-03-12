import groovy.json.JsonSlurper

class Helper {
    static getApiAsJson(url){
        def get = new URL(url).openConnection()
        def getRC = get.getResponseCode()
        def response = new JsonSlurper().parseText(get.getInputStream().getText())
        return response
    }
}
