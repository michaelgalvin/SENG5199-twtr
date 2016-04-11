/**
 * Created by cruzr004 on 4/10/16.
 */
angular.module('app').controller('UserDetailCtrl', ['$scope', '$location', '$resource', function ($scope, $location, $resource) {
    var UserDetailQuery = $resource('/api/account/show/:query', {query: $location.$$search.q});
    $scope.user = UserDetailQuery.query(); //get({handle:$location.$$search.q});
}]);