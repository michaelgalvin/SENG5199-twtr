angular.module('app')

    // configure the routes
    .config(function ($routeProvider) {

        $routeProvider
            .when('/login', {
                templateUrl: '/app/login.htm',
                controller: 'loginController'
            })
            .when('/home/:handle?', {
                templateUrl: '/app/home.htm',
                controller: 'homeController'
            })
            .when('/feed', {
                templateUrl: '/app/feed.htm',
                controller: 'feedController'
            })
            .otherwise({
                redirectTo: '/feed'
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


