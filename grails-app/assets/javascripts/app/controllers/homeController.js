angular.module('app').controller('homeController', function ($scope, $location, securityService) {
    $scope.greeting = 'Hello Stranger';
    $scope.searchGreeting = 'Enter search parameters';

    $scope.getUserDetail = function() {
        //"/api/message"(resources: "message")
        $location.url('/userDetail?q=' + encodeURIComponent($scope.query));
    };
});