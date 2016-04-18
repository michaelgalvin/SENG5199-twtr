angular.module('app').controller('searchController', ['$scope', '$location', function ($scope, $location) {

    $scope.searchByHandle = function() {
        $location.url('/posterMessages?q=' + encodeURIComponent($scope.query));
    };

    $scope.searchByTerm = function() {
        $location.url('/filteredMessages?q=' + encodeURIComponent($scope.query));
    };

    $scope.getUserDetail = function() {
        $location.url('/userDetail?q=' + encodeURIComponent($scope.query));
    };
}]);