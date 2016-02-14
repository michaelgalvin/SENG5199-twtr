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

    /* Saving an account missing any of the required values of
        - handle
        - email,
        - password and
        - name will fail
     */
    def "A2:Test account validation"() {
        when:
        def account = new Account(handle: handle, email: email, password: password, name: name)

        then:
        result == account.validate()

        where:
        handle | email              | password    | name   | result
        ''     | 'galvi024@umn.com' | '1234567aH' | 'Mike' | false
        'mike' | ''                 | '1234567aH' | 'Mike' | false
        'mike' | 'galvi024@umn.edu' | ''          | 'Mike' | false
        'mike' | 'galvi024@umn.edu' | '1234567aH' | ''     | false
    }

    /* Passwords must be
        - 8-16 characters
       and have
        - at least 1 number,
        - at least one lower-case letter,
        - at least 1 upper-case letter
        - whitespace characters are not allowed
     */
    def "A3:Test password validation"() {
        when:
        def account = new Account(handle: handle, email: email, password: password, name: name)

        then:
        result == account.validate()

        where:
        handle | email              | password                            | name            | result
        'mike' | 'galvi024@umn.edu' | '1Aasdad'                           | 'Magdalena'     | false
        'mike' | 'galvi024@umn.edu' | '1Aabcdefghijklmnopqrstuvwxyz'      | 'Magdalena'     | false
        'mike' | 'galvi024@umn.edu' | '1Aa2345678901234567890123456'      | 'Magdalena'     | false
        'mike' | 'galvi024@umn.edu' | '1Aabcdefghijklmn'                  | 'Magdalena'     | true
        'mike' | 'galvi024@umn.edu' | '1a23456789012345'                  | 'Magdalena'     | false
        'mike' | 'galvi024@umn.edu' | '1A23456789012345'                  | 'Magdalena'     | false
        'mike' | 'galvi024@umn.edu' | '1abcdefghijklmno'                  | 'Magdalena'     | false
        'mike' | 'galvi024@umn.edu' | '1Aa             '                  | 'Magdalena'     | false
        'mike' | 'galvi024@umn.edu' | '1Abcd^fghijklmno'                  | 'Magdalena'     | true
        'mike' | 'galvi024@umn.edu' | '1asdadas0000000WW'                 | 'Magdalena'     | false

    }

    def cleanup() {
    }
}