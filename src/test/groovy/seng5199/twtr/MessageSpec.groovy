package seng5199.twtr

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Message)
@Mock(Account)
class MessageSpec extends Specification {

    void "M1: Save a message with a valid account and message"(){
        setup:
//        def name = new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike')

//        def acc = Mock(Account)
//        println "Account id is:" + acc.id

        when:
        def message = new Message(text: "First tweet", author: "Mike")
        message.save()

        then:
        message.validate()
    }
}
