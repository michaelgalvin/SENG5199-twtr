/**
 * Created by cruzr004 on 4/10/16.
 */
angular.module('app').controller('UserDetailCtrl', ['$scope', '$location', '$resource', function ($scope, $location, $resource) {

    var UserDetailQuery = $resource('/api/account/show/:query');
    $scope.user = UserDetailQuery.get({query: $location.$$search.q});
}]);