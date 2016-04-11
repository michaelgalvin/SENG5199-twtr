angular.module('app').controller('searchController', ['$scope', '$location', function ($scope, $location) {

    $scope.searchByHandle = function() {
        //"/api/message"(resources: "message")
        $location.url('/posterMessages?q=' + encodeURIComponent($scope.query));
    };

    $scope.searchByTerm = function() {
        //"/api/message"(resources: "message")
        $location.url('/filteredMessages?q=' + encodeURIComponent($scope.query));
    };

}]);