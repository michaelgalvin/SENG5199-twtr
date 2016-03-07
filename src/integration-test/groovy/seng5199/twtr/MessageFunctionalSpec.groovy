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
//grails test-app

//    def 'M1: Create a REST endpoint will create a Message given a specified Account id or handle and message text'() {
//        given:
//        def acc = new Account(handle: 'uniqueHandle', email: 'handle@umn.edu', password: '1234567Ad', name: 'Debbie')
//        acc.save()
//        def message = Message(text: 'Hi', author: acc)
//        def json1 = acc as JSON
//        def json2 = message as JSON
//
//        when:
//        def resp1 = restClient.post(path: '/account', body: json1 as String, requestContentType: 'application/json')
//        def resp2 = restClient.post(path: '/message', body: json2 as String, requestContentType: 'application/json')
//
//        then:
//        resp1.status == 201
//        resp1.data
//
//        resp2.status == 201
//        resp2.data
//    }
}