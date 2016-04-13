angular.module('app').controller('homeController', function ($scope, $location, securityService) {
    $scope.greeting = 'Hello Stranger';
    $scope.searchGreeting = 'Enter search parameters';

    $scope.getUserDetail = function() {
        //"/api/message"(resources: "message")
        $location.url('/userDetail?q=' + encodeURIComponent($scope.query));
    };

    $scope.$watch(securityService.currentUser, function (value, oldValue) {

        if (!value && oldValue) {
            console.log("Disconnect");
            $location.path('/login');
        }

        if (value) {
            console.log("Connect");
            //Do something when the user is connected
        }
    })
});