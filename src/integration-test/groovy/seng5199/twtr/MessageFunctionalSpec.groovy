package seng5199.twtr

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Stepwise

/**
 * Created by galvi024 on 2/26/16.
 */

@Integration
@Stepwise
class MessageFunctionalSpec extends GebSpec {

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }

    def 'M1: Create a REST endpoint will create a Message given a specified Account id or handle and message text'() {
        given:
        Account acc = new Account(handle: 'myHandle', email: 'my@umn.edu', password: '1234567Ad', name: 'Leopold')
        def json = acc as JSON

        when:
        def resp = restClient.post(path: '/api/account', body: json as String, requestContentType: 'application/json')

        then:
        resp.status == 201
        resp.data.id
        resp.data.handle == 'myHandle'
        resp.data.email == 'my@umn.edu'
        resp.data.password == '1234567Ad'
        resp.data.name == 'Leopold'

        when: "given a specified Account id"
        Message message = new Message(text: 'Hi', author: [id: resp.data.id])
        def json2 = message as JSON
        def resp2 = restClient.post(path: '/api/message', body: json2 as String, requestContentType: 'application/json')

        then:
        resp2.status == 200

//        when:"given a specified Account handle"
//        Message message2 = new Message(text: 'Hi', author:[handle:resp.data.handle])
//        def json3 = message2 as JSON
//        def resp3 = restClient.post(path: '/api/message', body: json3 as String, requestContentType: 'application/json')
//
//        then:
//        resp3.status == 200
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
        //The endpoint must honor a limit parameter that caps the number of responses. The default limit is 10. (data-driven test)
    }
}