package seng5199.twtr

class Message {
    String text
    Account author
    Date dateCreated
    Date lastUpdated

    static constraints = {
        text minSize: 1, maxSize: 40
        author nullable: false
    }
}
