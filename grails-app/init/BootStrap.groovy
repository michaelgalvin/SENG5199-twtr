import seng5199.twtr.Account

class BootStrap {

    def init = { servletContext ->
        // Define some intial users
        new Account(handle: 'groovyNewbie', email: 'newb@gmail.com', password: '12345678aH', name: 'Mike').save()
        new Account(handle: 'groovyNewbie2', email: 'newb@gmail2.com', password: '12345678aH', name: 'Newb').save()

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
        new seng5199.twtr.Message(author: 2, text: 'Hi 5').save()
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
