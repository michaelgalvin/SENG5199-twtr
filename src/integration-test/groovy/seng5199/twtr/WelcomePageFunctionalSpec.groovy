package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import spock.lang.Ignore
import spock.lang.Stepwise

/**
 * Created by cruzr004 on 4/1/16.
 */


@Integration
@Stepwise
class WelcomePageFunctionalSpec extends GebSpec{
    def 'welcome page displays welcome message'() {
        when:
        go '/'

        then: 'Static welcome displayed properly'
        $('h1').first().text() == 'Welcome to the sample Grails 3 Angular App'

        and: 'Angular generated test displayed properly'
        $('h2').first().text() == 'Hello Stranger'
    }
}
