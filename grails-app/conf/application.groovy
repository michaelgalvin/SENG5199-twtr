/**
 * Created by galvi024 on 3/29/16.
 */
grails.plugin.springsecurity.filterChain.chainMap = [

        //Stateless chain
        [
                pattern: '/account/**',
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
                [pattern: '/account/**', access: ['ROLE_READ']]
        ]
]
