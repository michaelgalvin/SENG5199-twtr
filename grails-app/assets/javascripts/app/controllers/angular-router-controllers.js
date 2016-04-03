/**
 * Created by galvi024 on 4/2/16.
 */

// Define a controller called 'welcomeController'
angular.module('app').controller('welcomeController', function($scope) {
    $scope.welcomeGreeting = 'Hello Stranger'
});

// Define a controller called 'searchController'
angular.module('app').controller('searchController', function($scope) {
    $scope.searchGreeting = 'Enter search parameters'
});