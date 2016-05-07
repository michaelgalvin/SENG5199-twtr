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

    def 'R1. Use a alert control from the Angular UI library to display an info message saying Message Posted!'() {
        when:
        $('#send-message-link')[0].click();

        then:
        waitFor { $('#twtr-text') }
        $("#twtr-text").value("GOOG NEWS we are done with the WEB class..lets celebrate!.")

        when:
        $('#twtr-check').click()//Click the checkbox
        $('#message-button')[0].click();//Click the button

        then:
        waitFor {$('#message-alert').text() == "Message Posted!"
        }

    }
def 'R2. Use Angular validation to validate a message prior to posting it to the server via the REST API (client side validation).'() {

    when:
    $('#send-message-link')[0].click();

    then:
    waitFor{ $('#twtr-text') }
    $("#twtr-text").value("Dogs have masters. Cats have staff.")

    when:
    //$('#twtr-check').click()//Click the checkbox
    $('#message-button')[0].click();//Click the button

    then:
    waitFor { $('#message-alert').text() == "Only humans can tweet using this application!" }

    when:
    $('#twtr-check').click()//Click the checkbox
    $("#twtr-text").value("OK need to check if I was a human ..ja ja - lets celebrate!.")
    $('#message-button')[0].click();//Click the button

    then:
    waitFor { $('#message-alert').text() == "Message Posted!" }
}



}
