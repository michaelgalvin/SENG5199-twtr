package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

/**
 * Created by galvi024 on 4/13/16.
 */
@Integration
class UserDetailFunctionalSpec extends GebSpec {
    def setup() {
        go '/'
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click() //Click the button
        sleep(1000)
    }

    def 'U1: User’s detail page will display the user’s name as well as a scrollable list of that user’s postings'() {
        when:
        $('#home-link')[0].click();
        sleep(1000)
        then:
        $("#home-form input[id=inputHandle]").value("admin")
        $('#search-by-user')[0].click();
        sleep(1000)
        $('#user-name').text() == 'Admin'
    }

    def 'U2: User’s detail page will provide a way for the logged in user to follow the detail user'() {
        when:
        $('#home-link')[0].click();
        sleep(1000)
        then:
        $("#home-form input[id=inputHandle]").value("groovyNewbie2")
        $('#search-by-user')[0].click();
        sleep(2000)
        $('#follow')[0].click()
        sleep(1000)
        $('#follow').text() == 'Following'
    }

    def '' () {

    }
}
