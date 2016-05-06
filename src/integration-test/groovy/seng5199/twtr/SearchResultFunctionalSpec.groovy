package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import java.text.SimpleDateFormat

@Integration
class SearchResultFunctionalSpec extends GebSpec {

    def setup() {
        when:
        go '/'
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click() //Click the button

        then:
        waitFor { $('h2').first().text() == 'Enter your search' }
    }

    def 'S1-1(and R5): Provide a search box for finding messages by message poster'() {
        when: 'Enter a handle into the searchbox'
        $("#searchForm input[id=searchBox]").value("groovyNewbie")
        $("#searchForm button[id=searchUser]").click() //Click the button

        then: 'Should be taken to the Search Result page'
        waitFor { $('h2').first().text() == 'Search Results' }
        waitFor { $(".searchResultContainer").find("td", id: "message-text-content").text() == 'Hello 1' }
        // R5. Use the AngularJS date filter to format the date of a message in the feed in this style:
        // Mar 16. Validate with a functional test.
        def date = new Date()
        def sdf = new SimpleDateFormat("MMM d")
        waitFor { $(".searchResultContainer").find("td", id: "message-date-created").text() == sdf.format(date).toString() }
    }

    def 'Combination of S1-2, S3 and S4'() {
        //S1-2: Provide a search box for finding messages by message contents
        //S3: Search result messages will display the message contents as well as the posting user.
        when: 'Enter a message into the search box'
        $("#searchForm input[id=searchBox]").value("Hello 1")
        $("#searchForm button[id=searchMessage]").click() //Click the button

        then:
        waitFor { $(".searchResultContainer").find("td", id: "message-author-content").text() == "groovyNewbie" }
        $(".searchResultContainer").find("td", id: "message-text-content").text() == "Hello 1"
        //Check for a scrollbar
        // Shout out to Paul Michalek for help on this JS
        def scrollTest =
                "var style = window.getComputedStyle(document.getElementById('message-scrollbar'), null);" +
                        "var vHeight = parseInt(style.getPropertyValue('height'));" +
                        "var sHeight = document.getElementById('message-scrollbar').scrollHeight;" +
                        "return (sHeight > vHeight) ? true : false;"
        browser.driver.executeScript(scrollTest)

        //S4: Clicking on the posting user’s name in a message will route to the user’s detail page
        when:
        $('#handle-link')[0].click();

        then:
        waitFor { $('label').first().text() == 'Message Count:' }
    }
}