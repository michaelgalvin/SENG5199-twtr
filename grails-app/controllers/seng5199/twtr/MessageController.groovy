package seng5199.twtr

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

class MessageController extends RestfulController {

    def allowedMethods = [save: 'POST']
    static responseFormats = ['json', 'xml']

    MessageController() {
        super(Message)
    }

    @Override
    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
        def messages = Message.findAll("from Message order by date_created", [max: 100])
        render messages as JSON
    }

    @Override
    def show() {
        def account = Account.get(params.id)
        def messages = Message.findAll("from Message where author=${account.id} order by date_created", [max: 10])

        if (account) {
            render messages as JSON
        } else {
            response.status = 404
        }
    }

    @Transactional
    def save(Message message) {
        if (message.hasErrors()) {
            respond message.errors
        } else {
            message.save flush: true

        }
    }
}
