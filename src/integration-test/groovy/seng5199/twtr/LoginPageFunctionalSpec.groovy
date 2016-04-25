package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

@Integration
class LoginPageFunctionalSpec extends GebSpec {
    def 'L1: When not logged in, route user to the login screen'() {
        when:
        go '#/login'

        then: 'Static login displayed properly'
        waitFor { $('h1').first().text() == 'TWTR: small bird noises' }

        and: 'Angular generated test displayed properly'
        $('h2').first().text() == 'Please Login'
    }


    def 'L2: Login screen allows a user to enter username and password to gain access'() {
        when: "User enters valid username and password"
        go '#/login'

        then:
        waitFor { $("#loginForm input[id=inputUsername]") }

        when:
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click()

        then: "They are redirected to the search page"
        waitFor { $('h2').text() == 'Enter your search' }
    }

    def 'L3: Invalid login will be rejected with an error message'() {
        when: "An invalid username and password is entered"
        go '#/login'

        then:
        waitFor { $("#loginForm input[id=inputUsername]") }

        when:
        $("#loginForm input[id=inputUsername]").value("invalidUser")
        $("#loginForm input[id=inputPassword]").value("BadPassword")
        $("#loginForm button[id=login]").click()

        then: "User should see an error message"
        waitFor { $('#login-error-msg').text() == 'Invalid login' }
    }
}
