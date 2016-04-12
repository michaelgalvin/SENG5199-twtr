package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

/**
 * Created by galvi024 on 4/12/16.
 */
@Integration
class LogoutFunctionalSpec extends GebSpec{
    def ''() {
        when: "User enters valid username and password, then click Logout"
        go '/'
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click()
        sleep(1000)
        $('#logout-link')[0].click();
        sleep(1000)

        then:
        $('h2').text() == "Sorry to see you go… etc"
    }
}
