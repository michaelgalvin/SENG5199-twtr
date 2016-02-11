package seng5199.twtr

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Account)
class AccountSpec extends Specification {

    def setup() {

    }

    def "A1: Valid Entries"() {
        when: ''
        def name = new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike')

        then: 'validation should pass'
        name.validate()
    }


    def "A2-A3:Test all the things!"() {
        when:
        def account = new Account(handle: handle, email: email, password: password, name: name)

        then:
        result == account.validate()

        where:
        handle | email              | password    | name   | result
        ''     | 'galvi024@umn.com' | '1234567aH' | 'Mike' | false
        'mike' | ''                 | '1234567aH' | 'Mike' | false
        'mike' | 'galvi024@umn.edu' | ''          | 'Mike' | false
        'mike' | 'galvi024@umn.edu' | 'asdadas'   | ''     | false
    }

    def cleanup() {
    }
}