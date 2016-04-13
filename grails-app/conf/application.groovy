/**
 * Created by galvi024 on 3/29/16.
 */

// without this setting you will only get the id and class property of nested objects
// when they are rendered to JSON
// from https://manbuildswebsite.com/2010/02/08/rendering-json-in-grails-part-2-plain-old-groovy-objects-and-domain-objects/
grails.converters.json.default.deep=true

grails.plugin.springsecurity.filterChain.chainMap = [

        //Stateless chain
        [
                pattern: '/api/account/**',
                filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
        ]
]

grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'seng5199.twtr.AuthenticationToken'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'

grails.plugin.springsecurity.userLookup.userDomainClassName = 'seng5199.twtr.Account'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'seng5199.twtr.AccountRole'
grails.plugin.springsecurity.authority.className = 'seng5199.twtr.Role'

grails.plugin.springsecurity.userLookup.usernamePropertyName = 'handle'
//grails.plugin.springsecurity.userLookup.passwordPropertyName = 'password'
//grails.plugin.springsecurity.password.algorithm ='MD5'

grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
        [
                [pattern: '/api/account/**', access: ['ROLE_READ']]
        ]
]
