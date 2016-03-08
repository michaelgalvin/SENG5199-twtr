class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
            }
        }
        "/api/account"(resources: "accountRest")
        "/api/message"(resources: "messageRest")
//        "/api/accountRest"(resources: 'account') {
//            "/api/messageRest"(resources: 'message')
//        }
        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
