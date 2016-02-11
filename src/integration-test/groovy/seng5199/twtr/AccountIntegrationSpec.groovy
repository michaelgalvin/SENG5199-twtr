package seng5199.twtr


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class AccountIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "Non-Unique handle"() {
        given:
        Account intAccount = new Account(handle: 'groovyNewby', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')

        when:
        intAccount.save()

        then: "Should fail trying to save with non-unique handle"
        Account badAccount = new Account(handle: 'groovyNewby', email: 'newb2@groovy.com', password: '1234567wE', name: 'Hermoine Granger')
        !badAccount.save()
    }

    def "Non-Unique email"() {
        given:
        Account intAccount = new Account(handle: 'groovyNewby', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')

        when:
        intAccount.save()

        then: "Should fail trying to save with non-unique email"
        Account badAccount = new Account(handle: 'groovyNewby2', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')
        !badAccount.save()
    }
}
