angular.module('app')

    // configure the routes
    .config(function ($routeProvider) {

        $routeProvider
            .when('/login', {
                templateUrl: '/app/login.htm',
                controller: 'loginController'
            })
            .when('/home', {
                templateUrl: '/app/home.htm',
                controller: 'homeController'
            })
            .when('/userDetail',{
                templateUrl: '/app/userDetail.htm',
                controller: 'UserDetailCtrl'
            })
            .when('/search', {
                templateUrl: '/app/search.htm',
                controller: 'searchController'
            })
            .when('/posterMessages',{
                templateUrl: '/app/searchResults.htm',
                controller: 'PosterMessagesCtrl'
            })
            .when('/filteredMessages',{
                templateUrl: '/app/searchResults.htm',
                controller: 'GrepMessagesCtrl'
            })
            .otherwise({
                redirectTo: '/search'
            })
    })

    // Protect all routes other than login
    .run(function ($rootScope, $location, securityService) {
        $rootScope.$on('$routeChangeStart', function (event, next) {
            if (next.$$route.originalPath != '/login') {
                if (!securityService.currentUser()) {
                    $location.path('/login');
                }
            }
        });
    });


