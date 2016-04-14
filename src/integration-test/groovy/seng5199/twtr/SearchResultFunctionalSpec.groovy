package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

@Integration
class SearchResultFunctionalSpec extends GebSpec {

    def setup() {
        when:
        go '/'
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click() //Click the button
        sleep(1000)
        then:
        $('h2').first().text() == 'Enter your search'
    }

    def 'S1-1: Provide a search box for finding messages by message poster'() {
        when: 'Enter a handle into the searchbox'
        $("#searchForm input[id=searchBox]").value("groovyNewbie")
        $("#searchForm button[id=searchUser]").click() //Click the button
        sleep(1000)

        then: 'Should be taken to the Search Result page'
        $('h2').first().text() == 'Search Results'
        $(".searchResultContainer").find("td", id: "message-text-content").text() == "Hello 1"
    }

    def 'Combination of S1-2, S3 and S4'() {
        //S1-2: Provide a search box for finding messages by message contents
        //S3: Search result messages will display the message contents as well as the posting user.
        when: 'Enter a message into the search box'
        $("#searchForm input[id=searchBox]").value("Hello 1")
        $("#searchForm button[id=searchMessage]").click() //Click the button
        sleep(1000)

        then:
        $(".searchResultContainer").find("td", id: "message-author-content").text() == "groovyNewbie"
        $(".searchResultContainer").find("td", id: "message-text-content").text() == "Hello 1"
//        $("#custom-scrollable").scrollTo(50, 0);
//        $('.custom-scrollable').get(0).scrollHeight;
//        $('message-scrollbar').scrollHeight != $('message-scrollbar').offsetHeight;
        println('Height is:')
        println($('#message-scrollbar').height)
//        js.browser.$('#message-scrollbar').scroll();
//        assert browser.driver.executeScript("$('#message-scrollbar').scroll(0,250);") " == 1

        //S4: Clicking on the posting user’s name in a message will route to the user’s detail page
        when:
        $('#handle-link')[0].click();
        sleep(1000)

        then:
        $('label').first().text() == 'Message Count:'
    }
}