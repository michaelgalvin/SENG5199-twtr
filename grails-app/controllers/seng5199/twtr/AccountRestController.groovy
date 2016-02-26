package seng5199.twtr

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

class AccountRestController extends RestfulController {
    def allowedMethods = [save: 'POST', delete: ['POST', 'DELETE']]
    static responseFormats = ['json', 'xml']

    AccountRestController() {
        super(Account)
    }

    def show() {
        render "There are " + Account.count() + " accounts in the DB.<br/>"
        render "Names are:<br/>"
        def accounts = Account.list()
        accounts.each { render it.handle + " " + it.email + " " + it.password + " " + it.name + "<br/>" }
    }

    @Transactional
    def save(Account account) {
        if (account.hasErrors()) {
            respond account.errors
        } else {
            account.save flush: true

        }
    }
}
