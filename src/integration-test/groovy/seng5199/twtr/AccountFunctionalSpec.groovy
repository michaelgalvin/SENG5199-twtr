package seng5199.twtr

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Stepwise

@Integration
@Stepwise
class AccountFunctionalSpec extends GebSpec {

    RESTClient restClient

    def setup() {
        restClient = new RESTClient(baseUrl)
    }


    def 'A1: Create a REST endpoint that receives JSON data to create an Account'() {
        given:
        def acc = new Account(handle: 'excitedToGraduate', email: 'grad@umn.edu', password: '1234567Ad', name: 'Graduate')
        def json = acc as JSON

        when:
        def resp = restClient.post(path: '/api/account', body: json as String, requestContentType: 'application/json')

        then:
        resp.status == 201
        resp.data.id
        resp.data.handle == 'excitedToGraduate'
        resp.data.email == 'grad@umn.edu'
        resp.data.password == '1234567Ad'
        resp.data.name == 'Graduate'
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
        def resp = restClient.get(path: "/api/account/show/${newAccount.data.id}")

        then:
        resp.status == 200
        resp.data.id
        resp.data.handle == 'newAccount'
        resp.data.email == 'new@umn.edu'
        resp.data.name == 'NewUser'

        when:
        def resp2 = restClient.get(path: "/api/account/show/${newAccount.data.handle}")

        then:
        resp2.status == 200
        resp2.data.id
        resp2.data.handle == 'newAccount'
        resp2.data.email == 'new@umn.edu'
        resp2.data.name == 'NewUser'
    }

    def 'F1: Create a REST endpoint that will allow one account to follow another'() {

        when:
        def account1 = restClient.get(path:"/api/account/1")
        def account2 = restClient.get(path:"/api/account/2")

        then:
        account1.data.id
        account2.data.id
        account1.status == 200
        account2.status == 200

        when:
        restClient.post(path: "/api/account/follow/${account2.data.id}", query: [fid: account1.data.id ])
        restClient.post(path: "/api/account/follow/${account1.data.id}", query: [fid: account2.data.id ])

        then:
        def aaccount1 = restClient.get(path:"/api/account/1").data
        def aaccount2 = restClient.get(path:"/api/account/2").data

        aaccount1.followers.findAll { it.id == aaccount2.id }
        aaccount2.followers.findAll { it.id == aaccount1.id }
    }

    def 'F2: For the endpoint created for requirement A3, add properties for total counts of follwers and following for the account.'(){
        given:
        def followAcc1 = new Account(handle: 'PossessedWeekly', email: 'final@gmail.com', password: '1234567Ad', name: 'Neo')
        def jsonf1 = followAcc1 as JSON
        def newFollow1 = restClient.post(path: '/api/account/', body: jsonf1 as String, requestContentType: 'application/json')
        followAcc1.id = newFollow1.data.id

        def followAcc2 = new Account(handle: 'TheNerd', email: 'todo@d.umn.edu', password: '1234567Ad', name: 'Trinity')
        def jsonf2 = followAcc2 as JSON
        def newFollow2 = restClient.post(path: '/api/account/', body: jsonf2 as String, requestContentType: 'application/json')
        followAcc2.id = newFollow2.data.id

        when:
        restClient.post(path: "/api/account/follow/${followAcc1.id}", query: [fid: followAcc2.id ])
        restClient.post(path: "/api/account/follow/${followAcc2.id}", query: [fid: followAcc1.id ])
        newFollow1 = restClient.get(path:"/api/account/1").data
        newFollow2 = restClient.get(path:"/api/account/2").data

        then:
        newFollow1.totalFollowers==1
        newFollow1.totalFollowing==1
        newFollow2.totalFollowers==1
        newFollow2.totalFollowing==1
    }

    def 'F3: Add an endpoint to get the followers for an account. '(){
        //'This will return the details about the followers (handle, name, email, id).
        // Add the limit and offset logic implemented for messages to this endpoint.
        given:
        def followAcc3 = new Account(handle: 'P2', email: 'p2@gmail.com', password: '1234567Ad', name: 'Neo')
        def jsonf3 = followAcc3 as JSON
        def newFollow3 = restClient.post(path: '/api/account/', body: jsonf3 as String, requestContentType: 'application/json')
        followAcc3.id = newFollow3.data.id

        def followAcc4 = new Account(handle: 'P3', email: 'p3@d.umn.edu', password: '1234567Ad', name: 'Trinity')
        def jsonf4 = followAcc4 as JSON
        def newFollow4 = restClient.post(path: '/api/account/', body: jsonf4 as String, requestContentType: 'application/json')
        followAcc4.id = newFollow4.data.id

        def followAcc5 = new Account(handle: 'P4', email: 'p4@d.umn.edu', password: '1234567Ad', name: 'Smith')
        def jsonf5 = followAcc5 as JSON
        def newFollow5 = restClient.post(path: '/api/account/', body: jsonf5 as String, requestContentType: 'application/json')
        followAcc5.id = newFollow5.data.id

        def followAcc6 = new Account(handle: 'P5', email: 'p5@d.umn.edu', password: '1234567Ad', name: 'Morpheous')
        def jsonf6 = followAcc6 as JSON
        def newFollow6 = restClient.post(path: '/api/account/', body: jsonf6 as String, requestContentType: 'application/json')
        followAcc6.id = newFollow6.data.id


        restClient.post(path: "/api/account/follow/${followAcc3.id}", query: [fid: followAcc5.id ])
        restClient.post(path: "/api/account/follow/${followAcc4.id}", query: [fid: followAcc5.id ])
        restClient.post(path: "/api/account/follow/${followAcc6.id}", query: [fid: followAcc5.id ])
        restClient.post(path: "/api/account/follow/${followAcc5.id}", query: [fid: followAcc3.id ])
        restClient.post(path: "/api/account/follow/${followAcc5.id}", query: [fid: followAcc4.id ])
        restClient.post(path: "/api/account/follow/${followAcc5.id}", query: [fid: followAcc6.id ])

        when:
        def following5 = restClient.get(path: "/api/account/following/${followAcc5.id}").data

        then:
        following5.size == 3
        following5.find{ it.id == followAcc3.id }
        following5.find{ it.id == followAcc4.id }
        following5.find{ it.id == followAcc6.id }
    }
//
//    def 'F4: Create a ‘feed’ endpoint which will return the most recent messages by Accounts being followed by an Account.'(){
//        //Include a response limit parameter. Include a parameter to only look for messages after a specified date.
//    }

}