import seng5199.twtr.Account

class BootStrap {

    def init = { servletContext ->
//        "/api/account"(resources: 'accountRest')
        new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike').save()
        new Account(handle: 'groovyNewbie2', email: 'newb@gmail2.com', password: '12345678aH', name: 'Newb').save()
    }
    def destroy = {
    }
}
