package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

/**
 * Created by galvi024 on 4/12/16.
 */
@Integration
class LogoutFunctionalSpec extends GebSpec{
    def 'N3: Logout - clicking this should bring you to the login screen and provide a helpful message ‘Sorry to see you go… etc’'() {
        when: "User enters valid username and password, then click Logout"
        go '/'
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click()
        sleep(1000)
        $('#logout-link')[0].click();
        sleep(1000)

        then:
        $('#logout-msg').text() == "He's dead, Jim."
    }
}
