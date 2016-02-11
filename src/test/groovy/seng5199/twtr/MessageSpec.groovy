package seng5199.twtr

import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Message)
@Mock(Account)
class MessageSpec extends Specification {

    def "M1: Save a message with a valid account and message"() {

        when:
        Account acc = new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike')
        def message = new Message(text: "First tweet", author: acc)
        message.save()

        then:
        if (!message.validate()) {
            message.errors.each {
                println it
            }
        }
    }

    def "M2: Message text is not blank and <= 40 chars"() {
        when:
        def message = new Message(text: text, author: author)

        then:
        if (result == !message.validate()) {
            message.errors.each {
                println it
            }
        }
        where:
        text     | author        | result
        ''       | Mock(Account) | false
        "a"      | Mock(Account) | true
        "a" * 40 | Mock(Account) | true
        "a" * 41 | Mock(Account) | false
    }

}
