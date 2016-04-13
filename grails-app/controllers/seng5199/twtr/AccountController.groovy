package seng5199.twtr

import grails.rest.RestfulController
import grails.converters.JSON
import groovy.json.JsonOutput

class AccountController extends RestfulController {
    def allowedMethods = [save: 'POST', delete: ['POST', 'DELETE']]
    static responseFormats = ['json', 'xml']

    AccountController() {
        super(Account)
    }

    @Override
    def show() {
        def idIsNumber = (params.id as String).isNumber()
        if (idIsNumber) {
            respond Account.get(params.id)
        } else {
            respond Account.findByHandle(params.id)
        }
    }

    def follow() {

        if (!params.id) {
            response.status = 422
            return
        }

        def userId = (params.id as String).isNumber()

        if (!userId) {
            response.status = 404
            return
        }

        def user = Account.get(params.id)

        if (!user) {
            response.status = 404
            return
        }

        def toFollowId
        def toFollow

        if (params.fid && (params.fid as String).isNumber()) {
            toFollowId = Account.get(params.fid)
            toFollow = Account.get(params.fid)
        }

        if (!toFollowId || !toFollow) {
            response.status = 200
            def qtyResponse = JSON.parse('{ "followers": "' + user.followers.size() + '"}')
            respond qtyResponse
        }

        toFollow.addToFollowers(user)
        toFollow.totalFollowers++
        toFollow.save()
        user.addToFollowing(toFollow)
        user.totalFollowing++
        user.save()

        response.status = 200
        respond user
    }

    def following() {

        if (!params.id) {
            response.status = 422
            return
        }

        def user
        if ((params.id as String).isNumber())
            user = Account.get(params.id)

        if (!user) {
            response.status = 404
            return
        }

        def criteria = Account.createCriteria()
        ArrayList<Account> following = criteria.list {
            following { eq("id", user.id) }
        }
        render following as JSON
    }

    def save(Account account) {
        if (!account.save(flush: true)) {
            response.status = 422
            respond account.errors
        }

        response.status = 201
        respond account
    }
}