package seng5199.twtr

import grails.rest.RestfulController

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
}