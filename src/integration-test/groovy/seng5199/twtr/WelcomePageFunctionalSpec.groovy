package seng5199.twtr

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import spock.lang.Ignore


/**
 * Created by cruzr004 on 4/1/16.
 */


@Integration
class WelcomePageFunctionalSpec extends GebSpec{
    def 'welcome page displays welcome message'() {
        when:
        go '/'

        then: 'Static welcome displayed properly'
        $('h1').first().text() == 'TWTR: small bird noises'

        and: 'Angular generated test displayed properly'
        $('h2').first().text() == 'Hello Stranger'
    }
}
