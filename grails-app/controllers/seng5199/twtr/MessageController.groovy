package seng5199.twtr

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

class MessageController extends RestfulController {

    Class<Message> resource

    def allowedMethods = [save: 'POST']
    static responseFormats = ['json', 'xml']

    MessageController() {
        super(Message)
    }

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def messages = Message.findAll("from Message order by date_created", [max: params.max])
        render messages as JSON
    }

    @Override
    def show() {
        def account = Account.get(params.id)
        def messages = Message.findAll("from Message where author=${account.id} order by date_created", [max: Math.min(params.int('max') ?: 10, 100)])

        if (account) {
            render messages as JSON
        } else {
            response.status = 404
        }
    }

////    @Transactional
//    def save() {
//        def author
//        if (message.author.handle)
//            author = Account.findByHandle(message.author.handle)
//        else if (message.author.id && (message.author.id as String).isNumber())
//            author = Account.get(message.author.id)
//
//        if (author == null) {
//            // every message must have an author
//            respond "You must log in first before you can post a message."
//        }
//
//        if (message.hasErrors()) {
//            respond message.errors
//        } else {
//            message.author = author
//            message.save flush: true
//        }
//    }

}
