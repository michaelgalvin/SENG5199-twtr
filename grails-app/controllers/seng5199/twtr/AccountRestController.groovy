package seng5199.twtr

import grails.converters.JSON
import grails.rest.RestfulController

class AccountRestController extends RestfulController {
    def allowedMethods = [save: 'POST', delete: ['POST', 'DELETE']]
    static responseFormats = ['json', 'xml']

    AccountRestController() {
        super(Account)
    }

    @Override
    def show() {
        def idIsNumber = (params.id as String).isNumber()
        if (idIsNumber) {
            println("idIsNumber was true..." + params.id)
            Account.findById(params.id)
        } else {
            println("idIsNumber was false..." + params.id)
            Account.findByHandle(params.id)
        }
    }
}