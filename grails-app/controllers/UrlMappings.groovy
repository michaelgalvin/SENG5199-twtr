class UrlMappings {

    static mappings = {


        "/api/$controller/$action?/$id?(.$format)?" {
            constraints {
            }
            //message(sort:'date_created', order:'desc')
        }

        "/api/account"(resources: "account")
        "/api/account/follow/$id?"(controller: "account", action: "follow")
        "/api/account/following/$id?"(controller: "account", action: "following")
        "/api/message"(resources: "message")

        "/api/message/search/"(controller: "message", action: "search")

//        "/"(view: "/index")
//        "500"(view: '/error')
//        "404"(view: '/notFound')
        "/"(view: 'index')
        "500"(controller: 'Error', action: 'internalServerError')
        "404"(controller: 'Error', action: 'notFound')
        "401"(controller: 'Error', action: 'unauthorized')
        "403"(controller: 'Error', action: 'forbidden')
    }
}
