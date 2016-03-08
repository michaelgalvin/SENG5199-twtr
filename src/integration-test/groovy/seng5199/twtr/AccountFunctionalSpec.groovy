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
class AccountFunctionalSpec extends GebSpec {
    @Shared
    def accId

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }

    def 'A1: Create a REST endpoint that receives JSON data to create an Account'() {
        given:
        def acc = new Account(handle: 'exitedToGraduate', email: 'grad@umn.edu', password: '1234567Ad', name: 'Graduat')
        def json = acc as JSON

        when:
        def resp = restClient.post(path: '/api/account', body: json as String, requestContentType: 'application/json')

        then:
        resp.status == 201
        resp.data.id
        resp.data.handle == 'exitedToGraduate'
        resp.data.email == 'grad@umn.edu'
        resp.data.password == '1234567Ad'
        resp.data.name == 'Graduat'
    }

    def 'A2: Return an error response from the create Account endpoint if the account values are invalid #description'() {
        given:
        def acc = new Account(handle: handle, email: email, password: password, name: user)
        def json = acc as JSON

        when:
        restClient.post(path: '/api/account', body: json as String, requestContentType: 'application/json')

        then:
        HttpResponseException error = thrown(HttpResponseException)
        error.statusCode == 422

        where:
        description    | handle | email              | password    | user
        'Bad handle'   | ''     | 'galvi024@umn.edu' | '1234567Ad' | 'mike'
        'Bad email'    | 'mike' | 'mike.umn.edu'     | '1234567Ad' | 'mike'
        'Bad password' | 'mike' | 'galvi024@umn.edu' | '1'         | 'mike'
        'Bad user'     | 'mike' | 'galvi024@umn.edu' | '1'         | ''
    }

    def 'A3: Create a REST endpoint that returns JSON data with Account values for a user based on an account id or handle address.'() {
        given:
        def acc2 = new Account(handle: 'newAccount', email: 'new@umn.edu', password: '1234567Ad', name: 'NewUser')
        def json2 = acc2 as JSON
        def newAccount = restClient.post(path: '/api/account', body: json2 as String, requestContentType: 'application/json')

        when:
        def resp = restClient.get(path: "/api/account/${newAccount.data.id}")

        then:
        resp.status == 200
        resp.data.id
        resp.data.handle == 'newAccount'
        resp.data.email == 'new@umn.edu'
        resp.data.name == 'NewUser'

        when:
        def resp2 = restClient.get(path: "/api/account/${newAccount.data.handle}")

        then:
        resp2.status == 200
        resp2.data.id
        resp2.data.handle == 'newAccount'
        resp2.data.email == 'new@umn.edu'
        resp2.data.name == 'NewUser'
    }
}