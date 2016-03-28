package seng5199.twtr

import grails.gorm.*
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
        def messages
        if (params.text) {
            messages = Message.findAll("from Message where text=${params.text} order by date_created", [max: params.max, offset: params.offset])
        }
        else {
            messages = Message.findAll("from Message order by date_created", [max: params.max, offset: params.offset])
        }
        render messages as JSON
    }

    def accnt(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        if (!params.id) {
            response.status = 400
            respond "An ID or handle is required to get messages by account."
        }

        def aparam = params.id
        def author

        // need to determine if id is handle or id
        author = Account.findByHandle(aparam)
        if (author == null)
            author = Account.get(aparam)

        if (author == null) {
            response.status = 404
            respond "No account found for " + aparam + "."
        }

        def messages = Message.findAll("from Message where author=${author.id} order by date_created", [max: Math.min(params.int('max') ?: 10, 100), offset: params.offset])

        if (messages.size == 0) {
            response.status = 404
        } else {
            render messages as JSON
        }
    }


    def search(Integer max) {

        params.max = Math.min(max ?: 10, 100)

        def query = params.q
        def messages

        if (query) {
            def result = Message.where {
                text =~ "%${query}%"
            }
            messages = result.list()
        } else {
            messages = Message.findAll("from Message order by date_created", [max: params.max, offset: params.offset])
        }
        render messages as JSON
    }

    def save(Message message) {
        def author = message.author;
        def validAccnt = author
        if (author) {
            validAccnt = Account.get(author.id)
            if (!validAccnt)
                validAccnt = Account.findByHandle(author.handle)
        }

        message.author = validAccnt

        if (!message.save(flush: true)) {
            response.status = 422
            respond message.errors
            return
        }

        response.status = 201
        respond message
    }

//
//    def list() {
//        params.max = Math.min(max ?: 10, 100)
//        def message = Message.list(max: params.max, offset: params.offset)
//        def word = params.text
//        def query = Message.where {
//            text =~ "%${word}%"
//        }
//       def results = query.list()
//    }

//    def list() {
//        def query = parm.query
//        params.max = Math.min(params.max ? params.int('max') : 2, 100)
//
//        def messageList = Message.createCriteria().list (params) {
//            if ( params.query ) {
//                ilike("text", query)
//            }
//        }
//
//        [messageInstanceList: messageList, messageInstanceTotal: messageList.totalCount]
//    }



}
