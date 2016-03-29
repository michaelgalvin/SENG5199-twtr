package seng5199.twtr

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

/**
 * Created by galvi024 on 2/26/16.
 */

@Ignore
@Integration
@Stepwise
class MessageFunctionalSpec extends GebSpec {

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }

    def 'M1: Create a REST endpoint will create a Message given a specified Account id and message text'() {
        given:
        Account acc = new Account(handle: 'myHandle', email: 'my@umn.edu', password: '1234567Ad', name: 'Leopold')
        def json = acc as JSON

        when: "creating a new account"
        def resp = restClient.post(path: '/api/account', body: json as String, requestContentType: 'application/json')

        then: "id of created account should match"
        resp.status == 201
        resp.data.id

        when: "given a message and a specific Account with only the Account id"
        Message message1 = new Message(text: 'Hi', author: acc)
        acc.id = resp.data.id
        def json2 = message1 as JSON
        def resp2 = restClient.post(path: '/api/message', body: json2 as String, requestContentType: 'application/json')

        then:
        resp2.status == 201
        resp2.data.id
    }

    def 'M1 (Part2): Create a REST endpoint will create a Message given a specified Account handle and message text'() {
        given:
        Account acc2 = new Account(handle: 'MagdalenaHandle', email: 'cgelkrl@umn.edu', password: '1234567Ad', name: 'Alan')
        def json3 = acc2 as JSON

        when: "creating a new account"
        def resp3 = restClient.post(path: '/api/account', body: json3 as String, requestContentType: 'application/json')

        then: "handle of created account should match"
        resp3.status == 201
        resp3.data.handle


        when: "given a message and a specific Account with only the Account handle"
        acc2.id = resp3.data.id
        Message message2 = new Message(text: 'Hola mundo', author: acc2)
        def json4 = message2 as JSON
        def resp4 = restClient.post(path: '/api/message', body: json4 as String, requestContentType: 'application/json')

        then:
        resp4.status == 201
     }

    def 'M2: Return an error response from the create Message endpoint if user is not found (data-driven test)'() {
        given: "Create an Account object, but don't post it. So Message should not be able to find that user"
        Account notFound = new Account(handle: 'notFoundHandle', email: 'nfh@umn.edu', password: '1234567Ad', name: 'UNF')

        when:
        Message notFoundMessage = new Message(text: 'Test tweet', author: [id: notFound.id])
        def notFoundJSON = notFoundMessage as JSON
        restClient.post(path: '/api/message', body: notFoundJSON as String, requestContentType: 'application/json')

        then:
        HttpResponseException error = thrown(HttpResponseException)
        error.statusCode == 422
    }

    def 'M2(part2): Return an error response from the create Message endpoint if message text is not valid (data-driven test)'() {
        given:
        Account M2acc = new Account(handle: 'handle', email: 'handle@umn.edu', password: '1234567Ad', name: 'Erik')
        def M2json = M2acc as JSON

        when:
        def M2resp = restClient.post(path: '/api/account', body: M2json as String, requestContentType: 'application/json')

        then:
        M2resp.status == 201
        M2resp.data.id

        when: "Pass too many characters to the text of a Message class"
        Message M2message = new Message(text: 'a' * 41, author: [id: M2resp.data.id])
        def M2json2 = M2message as JSON
        restClient.post(path: '/api/message', body: M2json2 as String, requestContentType: 'application/json')

        then:
        HttpResponseException error = thrown(HttpResponseException)
        error.statusCode == 422
    }

    def 'M3: Create a REST endpoint that will return the most recent messages for an Account.'() {
        //http://localhost:8080/api/message/acct/1
        //The endpoint must honor a limit parameter that caps the number of responses. The default limit is 10. (data-driven test)
        given: "Get account #1, then get their messages"
        def M3acc = restClient.get(path: '/api/account/1')

        when: "Should default to 10"
        def M3resp = restClient.get(path: "/api/message/accnt/${M3acc.data.id}")

        then:
        M3resp.data.size == 10

        when: "Should accept a limit"
        def M3resp2 = restClient.get(path: "/api/message/accnt/${M3acc.data.id}", query: [max: '12'])

        then:
        M3resp2.data.size == 12
    }

    def 'M4: Support an offset parameter into the recent Messages endpoint to provide paged responses.'() {
        given:
        def M4resp = restClient.get(path: "/api/message/accnt/1", query: [max: '2', offset: '5'])

        when:
        M4resp

        then:
        M4resp.data.size == 2
        M4resp.data.find { it -> it.id == 6 }
        M4resp.data.find { it -> it.id == 7 }
    }

    def 'M5: Create a REST endpoint that will search for messages containing a specified search term.'() {
        //Each response value will be a JSON object containing the Message details (text, date) as well as the Account (handle)
        given: 'Existing message by groovieNewbie2 with the text: \'Save the date\''

        when:
        def M5resp = restClient.get(path: "/api/message/search", query: [q: 'Save', max: '2', offset: '5'])

        then:
        M5resp.status == 200
        M5resp.data.size == 1
        //M5resp.data.author.id == 2
    }
}