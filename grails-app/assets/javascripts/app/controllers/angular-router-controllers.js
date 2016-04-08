 /* Stolen from http://stackoverflow.com/questions/20969835/angularjs-login-and-authentication-in-each-route-and-controller
     I have no idea if this will work...*/
    app.run(['$rootScope', '$location', 'authenticationService', function ($rootScope, $location, authenticationService) {
        var authPreventer = $rootScope.$on('$routeChangeStart', function (event) {

            if (!authenticationService.isLoggedIn()) {
                //Comment out these four lines if you want to disable
                // Authentication
                console.log('DENY');
                event.preventDefault();
                authPreventer(); //Stop listening for location changes
                $location.path('/login');
            }
            else {
                console.log('ALLOW');
                $location.path('/home');
            }
        });
    }]);