package seng5199.twtr

import grails.test.mixin.TestFor
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

@Ignore
@Unroll
@TestFor(Account)
class AccountSpec extends Specification {

    def "A1: Valid Entries"() {
        when: ''
        def accountsBeforeSave = Account.count()
        def name = new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike')

        then: 'validation should pass'
        name.validate()

        when:
        name.save()

        then:
        !name.hasErrors()
        name.id
        Account.count() == accountsBeforeSave + 1
    }

    /* Saving an account missing any of the required values of
        - handle
        - email,
        - password and
        - name will fail
     */
    def "A2:Test account validation: #description"() {
        when:
        def accountsBeforeSave = Account.count()
        def account = new Account(handle: handle, email: email, password: password, name: name)

        then:
        !account.validate()

        when:
        account.save()

        then:
        account.hasErrors()
        !account.id
        Account.count() == accountsBeforeSave

        where:
        description     | handle | email              | password    | name
        'Blank handle'  | ''     | 'galvi024@umn.com' | '1234567aH' | 'Mike'
        'Blank email'   | 'mike' | ''                 | '1234567aH' | 'Mike'
        'Blank password'| 'mike' | 'galvi024@umn.edu' | ''          | 'Mike'
        'Blank name'    | 'mike' | 'galvi024@umn.edu' | '1234567aH' | ''
    }

    /* Passwords must be
        - 8-16 characters
       and have
        - at least 1 number,
        - at least one lower-case letter,
        - at least 1 upper-case letter
        - whitespace characters are not allowed
     */
    def "A3:Test fails to validate or save invalid password: #description"() {
        when:
        def startingNumberOfAccounts = Account.count()
        def account = new Account(handle: 'mike', email: 'galvi024@umn.edu', password: password, name: 'Magdalena')

        then:
        !account.validate()

        when:
        account.save()

        then:
        !account.id
        account.hasErrors()
        Account.count() == startingNumberOfAccounts
        account.errors.hasFieldErrors('password')


        where:
        description        | password
        '7 chars'          | '1Aasdad'
        'too long letters' | '1Aabcdefghijklmnopqrstuvwxyz'
        'too long numbers' | '1Aa2345678901234567890123456'
        'no cap'           | '1a23456789012345'
        'no lower'         | '1A23456789012345'
        'no number'        | 'abcdefghijklmno'
        '3 chars'          | '1Aa             '
    }

    def "A3:Saves with password: #description"() {
        when:
        def startingNumberOfAccounts = Account.count()
        def account = new Account(handle: 'mike', email: 'galvi024@umn.edu', password: password, name: 'Magdalena')

        then:
        account.validate()

        when:
        account.save()

        then:
        account.id
        !account.hasErrors()
        Account.count() == startingNumberOfAccounts + 1
        !account.errors.hasFieldErrors('password')

        where:
        description   | password
        'ok'          | '1Aabcdefghijklmn'
        'ok with ^'   | '1Abcd^fghijklmno'

    }
}