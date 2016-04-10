/**
 * Created by galvi024 on 4/2/16.
 */
angular.module('app').config(function ($routeProvider) {
    $routeProvider
        .when('/login', {
            templateUrl: '/app/login.htm',
            controller: 'loginController'
        })
       // .when('/home/:handle?', {
        .when('/home/:handle?', {
            templateUrl: '/app/home.htm',
            controller: 'homeController'
        })
        .when('/feed', {
            templateUrl: '/app/feed.htm',
            controller: 'feedController'
        })
        // .when('/feed', {
        //     templateUrl: '/app/feed.html',
        //     controller: 'feedController'
        // })
        // .when('/detail', {
        //     templateUrl: '/app/detail.html',
        //     controller: 'detailController'
        // })
        .otherwise({
            redirectTo: '/feed'
        });
});