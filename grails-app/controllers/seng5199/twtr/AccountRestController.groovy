package seng5199.twtr

import grails.rest.RestfulController

class AccountRestController extends RestfulController {
    def allowedMethods = [save: 'POST', delete: ['POST', 'DELETE']]
    static responseFormats = ['json', 'xml']

    AccountRestController() {
        super(Account)
    }

    @Override
    def show() {
        // We pass which fields to be rendered with the includes attributes,
        // we exclude the class property for all responses.
        respond queryForResource(params.id), [includes: includeFields, excludes: ['class']]
    }

    @Override
    def index(final Integer max) {
                params.max = Math.min(max ?: 10, 100)
                respond listAllResources(params), [includes: includeFields, excludes: ['class']]
    }

    private getIncludeFields() {
                params.fields?.tokenize(',')
    }
}