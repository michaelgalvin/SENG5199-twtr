package seng5199.twtr


import grails.rest.RestfulController
import grails.transaction.Transactional

class MessageRestController extends RestfulController {

    def allowedMethods = [save: 'POST']
    static responseFormats = ['json', 'xml']

    MessageRestController() {
        super(Message)
    }

//    def show() {
//        render "There are " + Message.count() + " messages in the DB.<br/>"
//        render "Messages are:<br/>"
//        def message = Message.list()
//        message.each { render it.text + " " + it.author.Id + "<br/>" }
//    }

    @Transactional
    def save(Message message) {
        if (message.hasErrors()) {
            respond message.errors
        } else {
            message.save flush: true

        }
    }
}
