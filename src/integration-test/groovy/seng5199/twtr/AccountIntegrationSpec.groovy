package seng5199.twtr


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class AccountIntegrationSpec extends Specification {

    def "A4: Non-Unique handle"() {
        given:
        def startingAccounts = Account.count()
        Account intAccount = new Account(handle: 'groovyNewby', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')
        intAccount.save()
        Account badAccount = new Account(handle: 'groovyNewby', email: 'newb2@groovy.com', password: '1234567wE', name: 'Hermoine Granger')

        when:
        badAccount.save()

        then: "Should fail trying to save with non-unique handle"
        !badAccount.id
        badAccount.hasErrors()
        badAccount.errors.getFieldError('handle')
        Account.count() == startingAccounts + 1
    }

    def "A4: Non-Unique email"() {
        given:
        def startingAccounts = Account.count()
        Account intAccount = new Account(handle: 'groovyNewby', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')
        intAccount.save()
        Account badAccount = new Account(handle: 'groovyNewby2', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')

        when:
        badAccount.save()

        then: "Should fail trying to save with non-unique email"
        !badAccount.id
        badAccount.hasErrors()
        badAccount.errors.getFieldError('email')
        Account.count() == startingAccounts + 1
    }

    def "F1: Account may have multiple followers"() {
        given:
        Account me = new Account(handle: 'groovyNewby1', email: 'newb1@groovy.com', password: '1234567wE', name: 'user1')
        Account follower1 = new Account(handle: 'groovyNewby2', email: 'newb@groovy.com', password: '1234567wE', name: 'user2')
        Account follower2 = new Account(handle: 'groovyNewby3', email: 'newb3@groovy.com', password: '1234567wE', name: 'user3')
        Account follower3 = new Account(handle: 'groovyNewby4', email: 'newb4@groovy.com', password: '1234567wE', name: 'user4')

        when:
        me.addToFollowers(follower1)
        me.addToFollowers(follower2)
        me.addToFollowers(follower3)
        me.save()
        Account retrieved = Account.get(me.id)

        then:
        retrieved.followers.size() > 1
        retrieved.followers.find { it.handle == "groovyNewby2" }
        retrieved.followers.find { it.handle == "groovyNewby3" }
        retrieved.followers.find { it.handle == "groovyNewby4" }
    }


    def "F2: Two accounts may follow each other"() {
        given:
        Account me = new Account(handle: 'groovyNewby1', email: 'newb1@groovy.com', password: '1234567wE', name: 'user1')
        Account you = new Account(handle: 'groovyNewby2', email: 'newb@2groovy.com', password: '1234567wE', name: 'user2')

        when:
        me.addToFollowers(you)
        me.save()
        you.addToFollowers(me)
        you.save()
        me = Account.get(me.id)
        you = Account.get(you.id)

        then:
        you.followers.find { it.handle == me.handle }
        me.followers.find { it.handle == you.handle }


    }
}
