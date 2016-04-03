/**
 * Created by galvi024 on 4/2/16.
 */
app.config(function ($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: '/app/helloStranger.htm',
            controller: 'welcomeController'
        })
        // .when('/home', {
        //     templateUrl: '/app/home.htm',
        //     controller: 'mainController'
        // })
        // .when('/about', {
        //     templateUrl: '/app/about.html',
        //     controller: 'aboutController'
        // })
        // .when('/contact', {
        //     templateUrl: '/app/contact.html',
        //     controller: 'contactController'
        // })
        // .when('/attendee/:action?/:id?', {
        //     templateUrl: 'twtr/partials/attendee.html'
        // })
        .otherwise({
            redirectTo: '/home'
        });
});