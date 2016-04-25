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
        waitFor { $('#home-link') }
    }

    def 'U1: User’s detail page will display the user’s name as well as a scrollable list of that user’s postings'() {
        when:
        $('#home-link')[0].click();
        then:
        waitFor { $("#home-form input[id=inputHandle]").value("admin") }
        $('#search-by-user')[0].click();
        waitFor { $('#user-name').text() == 'Admin' }
    }

    def 'U2 & U3: User’s detail page will provide a way for the logged in user to follow the detail user'() {
        when:
        $('#home-link')[0].click();

        then:
        waitFor { $("#home-form input[id=inputHandle]").value("groovyNewbie2") }

        when:
        $('#search-by-user')[0].click();

        then:
        waitFor { $('#follow') }

        when:
        waitFor { $('#follow')[0].click() }

        //U3: When the logged in user is following the detail user, the detail page will display a message or icon indicating this
        then:
        waitFor { $('#follow').text() == 'Following' }
    }


    def 'U4: When the logged in user goes to their own detail page, they can edit their name and email' () {
        when:// Go myAccount/ page
        $('#my-account-link')[0].click();
        sleep(1000)
        then://Click the edit button
        $('#edit-button')[0].click();
        sleep(1000)// Enter new name and email
        $("#edit-user-form input[id=name-input]").value("NewName!!!!")
        $("#edit-user-form input[id=email-input]").value("newEmailAddress@somedomain.com")
        sleep(1000)

        when:
        $('#save-button')[0].click()
        sleep(1000)

        then://Verify that it changed
        $("#current-name").text() == 'NewName!!!!'
        $("#current-email").text() == 'newEmailAddress@somedomain.com'
        sleep(1000)

        when://Navigate away and come back to verify that it persisted to the DB
        $('#search-link')[0].click();
        sleep(1000)
        $('#my-account-link')[0].click();
        sleep(1000)
        then://New name and email should still be the same as above
        $("#current-name").text() == 'NewName!!!!'
        $("#current-email").text() == 'newEmailAddress@somedomain.com'
    }
}
