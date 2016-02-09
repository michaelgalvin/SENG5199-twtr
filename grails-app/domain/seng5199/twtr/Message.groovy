package seng5199.twtr

class Message {
    String text
    Long authorId
//    Date dateCreated

    static belongsTo = [accounts: Account]

    static constraints = {
        text minSize: 1, maxSize: 40
    }
}
