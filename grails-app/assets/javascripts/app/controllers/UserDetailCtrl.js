/**
 * Created by cruzr004 on 4/10/16.
 */
angular.module('app').controller('UserDetailCtrl', ['$scope', '$location', '$resource','securityService', function ($scope, $location, $resource, securityService) {

    var UserDetailQuery = $resource('/api/account/show/:query');

    var currentAuthToken = securityService.currentUser();
    $scope.loggedInUser = UserDetailQuery.get({query: currentAuthToken.username});

    var handleQuery = $location.$$search.q;
    $scope.user = UserDetailQuery.get({query: handleQuery});

    $scope.iAmFollowingDebug = false;
    $scope.toFollow = "Follow";
    $scope.user.$promise.then(function(data){
        $scope.user = data;
        var followerCount = $scope.user.followers.length;

        // 1) verify that there are followers and
        // 2) if there are, loop through and see if currentUser is one of them
        $scope.loggedInUser.$promise.then(function (data) {
            $scope.loggedInUser = data;
            var loggedInHandle = $scope.loggedInUser.handle.trim();
            console.log("You are " + loggedInHandle + " with id " + $scope.loggedInUser.id);
            if (followerCount>0) {
                for (var i=0;i<followerCount;i++) {
                    for (var i=0;i<followerCount;i++) {
                        if (loggedInHandle === $scope.user.followers[i].handle.trim()) {
                            console.log("You are following " + $scope.user.handle + " with id " + $scope.user.id);

                            // 3) change the indicator to "Following" if I am following
                            $scope.iAmFollowingDebug = true;
                            $scope.toFollow = "Following";
                            $('#follow').addClass('disabled');
                        }
                    }
                }

                if (!$scope.iAmFollowingDebug) {
                    console.log("You are not following " + $scope.user.handle + " with id " + $scope.user.id);
                }
            }
        });
    });

    $scope.followUser = function(myId, userId) {
        $scope.loggedInUser.$promise.then(function (data) {
            console.log("Logged in user id " + $scope.loggedInUser.id);
            console.log("Detail user id " + $scope.user.id);

            var FollowUser = $resource('/api/account/follow/:myId?fid=:userId',

                {myId: $scope.loggedInUser.id, userId: $scope.user.id}, {

                    post: {
                        method: 'POST',
                        params: {myId: $scope.loggedInUser.id, userId: $scope.user.id},
                        headers: { 'X-Auth-Token':currentAuthToken.token}
                    }
                }
            );

            var followUserOperation = FollowUser.save();
            followUserOperation.$promise.then(function (result, responseHeaders) {
                $scope.toFollow = "Following";
                $('#follow').addClass('disabled');
                console.log(responseHeaders);
            });
        });
    };


    // need to




    // 4) if false then clicking on it should make the loggedInUser follow the
    //    user displayed on the user detail page
    // $scope.iAmFollowingDebug = false;

    $resource('/api/message/accnt/:query', {query:handleQuery}).query().$promise.then(function(data){
        $scope.messages = data;
    });
}]);