class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
            }
            //message(sort:'date_created', order:'desc')
        }
        "/api/account"(resources: "account")
        "/api/message"(resources: "message"){
            "/api/message/$id?"(resource: "message")
        }

        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
