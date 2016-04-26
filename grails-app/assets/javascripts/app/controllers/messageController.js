angular.module('app').controller('messageController', ['$scope', '$location', '$resource', 'securityService', '$routeParams', function ($scope, $location, $resource, securityService, $routeParams) {

    // Mostly copied from UserDetailCtrl.js
    // Attempting to get username of current logged in user,
    // then send a POST to the messages api
    var handle = securityService.currentUser().username;
    console.log(handle)

    var UserDetailQuery = $resource('/api/account/show/:handle', {handle: '@username'});
    var currentAuthToken = securityService.currentUser();

    $scope.loggedInUser = UserDetailQuery.get({query: currentAuthToken.username});
    var handleQuery = $location.$$search.q;
    $scope.user = UserDetailQuery.get({query: handleQuery});

    $scope.user.$promise.then(function (data) {
        $scope.user = data;
        var followerCount = $scope.user.followers.length;

        $scope.loggedInUser.$promise.then(function (data) {
            $scope.loggedInUser = data;
            var loggedInHandle = $scope.loggedInUser.handle.trim();
            console.log("You are " + loggedInHandle + " with id " + $scope.loggedInUser.id);
        });

    });


}]);