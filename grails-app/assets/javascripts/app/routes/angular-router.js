/**
 * Created by galvi024 on 4/2/16.
 */
angular.module('app').config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/app/home.html',
            controller: 'homeController'
        })
        // .when('/login', {
        //     controller: 'loginCtrl'
        // })
        // .when('/search', {
        //     templateUrl: '/app/search.htm',
        //     controller: 'searchController'
        // })
        // .when('/about', {
        //     templateUrl: '/app/about.html',
        //     controller: 'aboutController'
        // })
        // .when('/contact', {
        //     templateUrl: '/app/contact.html',
        //     controller: 'contactController'
        // })
        .otherwise({
            redirectTo: '/'
        });
});