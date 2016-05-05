package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration

@Integration
class SendMessageFunctionalSpec extends GebSpec {
    def setup() {
        go '/'
        $("#loginForm input[id=inputUsername]").value("admin")
        $("#loginForm input[id=inputPassword]").value("admin")
        $("#loginForm button[id=login]").click() //Click the button
        waitFor { $('#send-message-link') }
        sleep(1000)//Breaks without this. Not sure why.
    }

    def 'R2. Use Angular validation to validate a message prior to posting it to the server via the REST API (client side validation).'() {
        when:
        $('#send-message-link')[0].click();

        then:
        waitFor{ $('#twtr-text') }
        $("#twtr-text").value("Dogs have masters. Cats have staff.")

        when:
        $('#twtr-check').click()//Click the checkbox
        $('#message-button')[0].click();//Click the button

        then:
        waitFor { $('#message-alert').text() == "Message Posted!" }

        when:
        $("#twtr-text").value("This message is waaaaaaaaaaaayyyyyyyyyyy toooooooooooooo loooooooooooonnnnnngggg.")

        then:
        waitFor { $('#message-alert').text() == "Your tweet can be only 40 characters long." }
    }
}