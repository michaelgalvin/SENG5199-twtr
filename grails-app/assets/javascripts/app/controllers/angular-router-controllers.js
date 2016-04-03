/**
 * Created by galvi024 on 4/2/16.
 */

// Define a controller called 'searchController'
angular.module('app').controller('welcomeController', function($scope) {
    $scope.greeting = 'Hello Stranger'
    $scope.searchGreeting = 'Enter search parameters'
});