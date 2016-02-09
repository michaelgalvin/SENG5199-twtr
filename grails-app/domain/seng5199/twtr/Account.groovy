package seng5199.twtr

class Account {
    String handle
    String email
    String password
    String name

    static hasMany = [messages: Message]

    static constraints = {
        handle nullable: false, unique: true
        email nullable: false, email: true, unique:true
        password nullable: false, matches:/((?=.*[^a-zA-Z])(?=.*[a-z])(?=.*[A-Z]).{8,16})/
        name nullable: false
    }
}
