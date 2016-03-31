import seng5199.twtr.Account
import seng5199.twtr.AccountRole
import seng5199.twtr.Role

class BootStrap {

    def init = { servletContext ->
        def admin = new Account(handle: 'admin', password: '12345678aH', email: 'admin@admin.com', name: "Admin").save(flush: true, failOnError: true)
        def role = new Role(authority: 'ROLE_READ').save(flush: true, failOnError: true)
        new AccountRole(user: admin, role: role).save(flush: true, failOnError: true)

        // Define some intial users
        def test1 = new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike').save()
        new AccountRole(user: test1, role: role).save(flush: true, failOnError: true)
        def  test2 = new Account(handle: 'groovyNewbie2', email: 'newby2@gmail.com', password: '12345678aH', name: 'Newb').save()
        new AccountRole(user: test2, role: role).save(flush: true, failOnError: true)
        def test3 = new Account(handle: 'PossessWeekly2', email: 'finallyPossessed@gmail.com',password: '12345678aH', name: 'Toro').save()
        new AccountRole(user: test3, role: role).save(flush: true, failOnError: true)
        def test4 = new Account(handle: 'TheNerd2', email: 'finalNerd@gmail.com',password: '12345678aH', name: 'Hope').save()
        new AccountRole(user: test4, role: role).save(flush: true, failOnError: true)
        def test5 = new Account(handle: 'Posse2', email: '123r@gmail.com',password: '12345678aH', name: 'Toomuch').save()
        new AccountRole(user: test5, role: role).save(flush: true, failOnError: true)
        def test6 = new Account(handle: 'Nerd2', email: '234hn@gmail.com',password: '12345678aH', name: 'totoro').save()
        new AccountRole(user: test6, role: role).save(flush: true, failOnError: true)

        new seng5199.twtr.Message(author: 1, text: 'Hello 1').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 2').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 3').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 4').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 5').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 6').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 7').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 8').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 9').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 10').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 11').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 12').save()
        new seng5199.twtr.Message(author: 1, text: 'Hello 13').save()

        new seng5199.twtr.Message(author: 2, text: 'Hi 1').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 2').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 3').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 4').save()
        new seng5199.twtr.Message(author: 2, text: 'abc defghigklmnop').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 6').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 7').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 8').save()
        new seng5199.twtr.Message(author: 2, text: 'Save the date 9').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 10').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 11').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 12').save()
        new seng5199.twtr.Message(author: 2, text: 'Hi 13').save()
    }
    def destroy = {

    }
}
