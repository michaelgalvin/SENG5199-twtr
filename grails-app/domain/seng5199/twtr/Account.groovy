package seng5199.twtr

import grails.rest.*

@Resource(uri='/account', formats=['json', 'xml'])
class Account {
    String handle
    String email
    String password
    String name

    static hasMany = [following: Account, followers: Account]

    static constraints = {
        handle nullable: false, unique: true
        email nullable: false, email: true, unique: true
        password nullable: false, matches: /((?=.*[^a-zA-Z])(?=.*[a-z])(?=.*[A-Z]).{8,16})/
        name nullable: false
    }
    //Register Account domain for JSON rendering
//    JSON.registerObjectMarshaller(Account) {
//        def output = [:]
//        output['id'] = it.id
//        output['reviewContent'] = it.reviewContent
//        output['reviewRating'] = it.reviewRating
//        output['dateCreated'] = dateFormatter.format(it.dateCreated)
//        output['reviewer'] = ["id": it.reviewer.id, "name": it.reviewer.getFullName()]
//        output['reviewedUser'] = ["id": it.reviewedUser.id, "name": it.reviewedUser.getFullName()]
//
//        return output;
//    }
}
