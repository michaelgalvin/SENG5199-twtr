package seng5199.twtr

class Message {
    String text
    Account author
    Date dateCreated


    static constraints = {
        text minSize: 1, maxSize: 40
    }
}
