package seng5199.twtr
import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

/**
 * Created by cruzr004 on 4/10/16.
 */
@Integration
class SearchPageFuctionalSpec extends GebSpec {

    def 'S1: Provide a search box for finding messages by message poster and message contents'() {
            when: "User enters valid username and password"
            go '/'
            $("#loginForm input[id=inputUsername]").value("admin")
            $("#loginForm input[id=inputPassword]").value("admin")
            $("#loginForm button[id=login]").click()
            sleep(1000)

            then: "They are redirected to the search page"
            $('h2').text() == 'Enter your search'
        }
    def 'S1 part-a: Provide a search box for finding messages by message poster'() {
        when: " when valid username and password is entered"
        go '/search'
        $("#form-signin input[id=inputHandle]").value("groovyNewbie")
        $("#form-signin button[id=searchUser]").click()
        sleep(1000)

        then: "User should see message poster by groovyNewbie"

        // This is the search results title
        $('#searchResults > h2').text() == 'Search Results'
    }

}
