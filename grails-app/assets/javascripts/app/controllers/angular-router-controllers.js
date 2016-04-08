/**
 * Created by galvi024 on 4/2/16.
 */

    /* Stolen from http://stackoverflow.com/questions/20969835/angularjs-login-and-authentication-in-each-route-and-controller
     I have no idea if this will work...*/
    app.run(['$rootScope', '$location', 'authenticationService', function ($rootScope, $location, authenticationService) {
        $rootScope.$on('$routeChangeStart', function (event) {

            if (!authenticationService.isLoggedIn()) {
                //console.log('DENY');
                //event.preventDefault();
                //$location.path('/login');
            }
            else {
                console.log('ALLOW');
                $location.path('/home');
            }
        });
    }]),
    app.controller('loginCtrl', ['$scope', 'authenticationService', function ($scope, authenticationService) {
        //submit
        $scope.login = function () {
            // Ask to the server, do your job and THEN set the user

            authenticationService.setUser(user); //Update the state of the user in the app
        };
    }]);