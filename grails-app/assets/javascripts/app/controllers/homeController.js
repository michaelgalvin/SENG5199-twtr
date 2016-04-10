angular.module('app').controller('feedController', function ($scope, $location, securityService) {
    $scope.greeting = 'Hello Stranger';
    $scope.searchGreeting = 'Enter search parameters';

    $scope.$watch(securityService.currentUser, function (value, oldValue) {

        if (!value && oldValue) {
            console.log("Disconnect");
            $location.path('/login');
        }

        if (value) {
            console.log("Connect");
            //Do something when the user is connected
        }

        // $scope.token = authenticationService.getToken;
        // if (!authenticationService.getToken) {
        //     console.log("No token defined...sending user to login page")
        //     $scope.forceLogin = function () {
        //         //Logic to log a user in goes here...
        //
        //     }
        // }
    })
});