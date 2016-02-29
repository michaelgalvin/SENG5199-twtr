import seng5199.twtr.Account

class BootStrap {

    def init = { servletContext ->
//        "/api/account"(resources: 'accountRest')
        new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike').save()
        new Account(handle: 'groovyNewbie2', email: 'newb@gmail2.com', password: '12345678aH', name: 'Newb').save()
    }
    def destroy = {
//    }
//    //Register Review domain for JSON rendering
//    JSON.registerObjectMarshaller(Account) { Account s ->
//        return [ id: s.id, title: s.title, artist: s.artist.name ]
//    }
//
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
    }
}
