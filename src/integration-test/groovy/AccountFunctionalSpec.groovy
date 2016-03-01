import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import seng5199.twtr.Account
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

    def 'Make sure the endpoint is responding and initialized with 2 objects'() {
        when:
        def resp = restClient.get(path: '/account')

        then:
        resp.status == 200
        resp.data.size() == 2
    }

    def 'A1: Create a REST endpoint that receives JSON data to create an Account'() {
        given:
        def acc = new Account(handle: 'exitedToGraduate', email: 'grad@umn.edu', password: '1234567Ad', name: 'Graduate')
        def json = acc as JSON

        when:
        def resp = restClient.post(path: '/account', body: json as String, requestContentType: 'application/json')

        then:
        resp.status == 201
        resp.data

        when:
        accId = resp.data.id

        then:
        accId
        resp.data.handle == 'exitedToGraduate'
        resp.data.name == 'Graduate'
    }

    def 'A3: Create a REST endpoint that returns JSON data with Account values for a user based on an account id or handle address.'() {
        when:
        def resp = restClient.get(path: "/account/${accId}")

        then:
        resp.status == 200
        resp.data.id == accId
        resp.data.name == 'Graduate'
        resp.data.handle == 'exitedToGraduate'
    }

    def 'A2: Return an error response from the create Account endpoint if the account values are invalid #description'() {
        given:
        def acc = new Account(handle: handle, email: email, password: password, name: 'testUser')
        def json = acc as JSON

        when:
        restClient.post(path: '/account', body: json as String, requestContentType: 'application/json')

        then:
        thrown HttpResponseException

        where:
        description    | handle | email              | password
        'Bad handle'   | ''     | 'galvi024@umn.edu' | '1234567Ad'
        'Bad email'    | 'mike' | 'mike.umn.edu'     | '1234567Ad'
        'Bad password' | 'mike' | 'galvi024@umn.edu' | '1'
    }
}