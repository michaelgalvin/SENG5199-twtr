package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

@Integration
class SearchResultFuntionalSpec extends GebSpec {

    def setup() {
        when:
        go '/'
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click()
        sleep(1000)
        then:
        $('h2').first().text() == 'Enter your search'
    }

    def 'S1-1: Provide a search box for finding messages by message poster'() {
        when:
        $("#searchForm input[id=searchBox]").value("groovyNewbie")
        $("#searchForm button[id=searchUser]").click()
        sleep(1000)

        then:
        $('h2').first().text() == 'Search Results'
        $(".searchResultContainer").find("td", id: "message-text-content").text() == "Hello 1"
    }

    def 'S1-2: Provide a search box for finding messages by message contents'() {
        when:
        $("#searchForm input[id=searchBox]").value("Hello 1")
        $("#searchForm button[id=searchMessage]").click()
        sleep(3000)

        then:
//        $(".searchResultContainer").find("td", id: "message-author-content").text() == "groovyNewbie"
        $(".searchResultContainer").find("td", id: "message-message-content").text() == "Hello 1"
    }

//    def 'S2: Display matching results in a scrollable area below the search box'() {
////        when:
//
//    }
//
//    def 'S3: Search result messages will display the message contents as well as the posting user.'() {
//        when:
//        $("#searchForm input[id=searchBox]").value("Hello 1")
//        $("#searchForm button[id=searchMessage]").click()
//        sleep(1000)
//
//        then:
//        $(".searchResultContainer").find("td", id: "message-author-content").text() == "groovyNewbie"
//        $(".searchResultContainer").find("td", id: "message-message-content").text() == "Hello 1"
//
//    }
//
//    def 'S4: Clicking on the posting user’s name in a message will route to the user’s detail page'() {
//
//    }

}