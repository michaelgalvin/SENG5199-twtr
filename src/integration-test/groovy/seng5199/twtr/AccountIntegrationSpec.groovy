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

    def "A4: Non-Unique handle"() {
        given:
        Account intAccount = new Account(handle: 'groovyNewby', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')

        when:
        intAccount.save()

        then: "Should fail trying to save with non-unique handle"
        Account badAccount = new Account(handle: 'groovyNewby', email: 'newb2@groovy.com', password: '1234567wE', name: 'Hermoine Granger')
        !badAccount.save()
    }

    def "A4: Non-Unique email"() {
        given:
        Account intAccount = new Account(handle: 'groovyNewby', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')

        when:
        intAccount.save()

        then: "Should fail trying to save with non-unique email"
        Account badAccount = new Account(handle: 'groovyNewby2', email: 'newb@groovy.com', password: '1234567wE', name: 'Hermoine Granger')
        !badAccount.save()
    }

    def "F1: Account may have multiple followers"() {
        given:
        Account        me = new Account(handle: 'groovyNewby1', email: 'newb1@groovy.com', password: '1234567wE', name: 'user1')
        Account follower1 = new Account(handle: 'groovyNewby2', email: 'newb@2groovy.com', password: '1234567wE', name: 'user2')
        Account follower2 = new Account(handle: 'groovyNewby3', email: 'newb3@groovy.com', password: '1234567wE', name: 'user3')
        Account follower3 = new Account(handle: 'groovyNewby4', email: 'newb4@groovy.com', password: '1234567wE', name: 'user4')

        when:
        def follow1 = me.addToFolloweres(follower1)
        def follow2 = me.addToFolloweres(follower2)
        def follow3 = me.addToFolloweres(follower3)
        follow1.save()
        follow2.save()
        follow3.save()

        then:
        me.followeres.size() == 3
//        println "Account has these followers :" + me.followeres.name
    }


    def "F2: Two accounts may follow each other"() {
        given:
        Account        me = new Account(handle: 'groovyNewby1', email: 'newb1@groovy.com', password: '1234567wE', name: 'user1')
        Account       you = new Account(handle: 'groovyNewby2', email: 'newb@2groovy.com', password: '1234567wE', name: 'user2')

        when:
        me.addToFolloweres(you)
        me.save()
        you.addToFolloweres(me)
        you.save()

        then:
        for (item in me.followeres.name.iterator()) {
            println item
        }
        println "Me has " + me.followeres.name + " following his account."
        println "You has " + you.followeres.name + " following her account."
    }
}
