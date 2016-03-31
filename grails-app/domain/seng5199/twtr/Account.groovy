package seng5199.twtr

class Account {

    transient springSecurityService

    String handle
    String email
    String password
    String name
    Set<Account> following
    Set<Account> followers
    int totalFollowers
    int totalFollowing
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    Set<Role> getAuthorities() {
        AccountRole.findAllByUser(this)*.role
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ?
                springSecurityService.encodePassword(password) :
                password
    }

    static hasMany = [following: Account, followers: Account]

    static constraints = {
        handle nullable: false, unique: true
        email nullable: false, email: true, unique: true
        password nullable: false//, matches: /((?=.*[^a-zA-Z])(?=.*[a-z])(?=.*[A-Z]).{8,16})/
        name nullable: false
    }
}
