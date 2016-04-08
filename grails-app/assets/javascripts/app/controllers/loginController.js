app.controller('loginController', ['$scope', 'authenticationService', function ($scope, authenticationService) {
    //submit
    $scope.login = function () {
        // Ask to the server, do your job and THEN set the user
        console.log("Inside login controller");
        $scope.message = "Inside login Controller via th scope message";
        
        authenticationService.setUser(user); //Update the state of the user in the app
    };
}]);