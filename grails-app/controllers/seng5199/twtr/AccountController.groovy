package seng5199.twtr

import grails.rest.RestfulController
import grails.converters.JSON

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
        if (!params.id || !params.fid) {
            response.status = 422
            return
        }

        def userId = (params.id as String).isNumber()
        def toFollowId = (params.fid as String).isNumber()

        if (!userId || !toFollowId) {
            response.status = 404
            return
        }

        def user = Account.get(params.id)
        def toFollow = Account.get(params.fid)
        toFollow.addToFollowers(user)
        toFollow.save()
        user.addToFollowing(toFollow)
        user.save()

        response.status = 200
        respond user
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