package seng5199.twtr

class Message {
    String text
    Account author

    static constraints = {
        text minSize: 1, maxSize: 40
        author nullable: false
    }
}
