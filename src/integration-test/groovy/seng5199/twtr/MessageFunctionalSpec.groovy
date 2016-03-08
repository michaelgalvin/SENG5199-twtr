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

        when:"given a specified Account id"
        Message message = new Message(text: 'Hi', author:[id:resp.data.id])
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
}