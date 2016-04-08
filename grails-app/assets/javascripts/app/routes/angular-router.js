/**
 * Created by galvi024 on 4/2/16.
 */
angular.module('app').config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/app/home.htm',
            controller: 'homeController'
        })
        .when('/login', {
            templateUrl: '/app/login.htm',
            controller: 'loginController'
        })
        .when('/searchMessage', {
            templateUrl: '/app/searchMessage.htm',
            controller: 'searchController'
        })
        // .when('/about', {
        //     templateUrl: '/app/about.html',
        //     controller: 'aboutController'
        // })
        // .when('/contact', {
        //     templateUrl: '/app/contact.html',
        //     controller: 'contactController'
        // })
        .otherwise({
            redirectTo: '/login'
        });
});