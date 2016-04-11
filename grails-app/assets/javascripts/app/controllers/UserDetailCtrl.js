/**
 * Created by cruzr004 on 4/10/16.
 */
angular.module('app').controller('UserDetailCtrl', ['$scope', '$location', '$resource','securityService', function ($scope, $location, $resource, securityService) {

    var handleQuery = $location.$$search.q;
    var currentHandle = securityService.currentUser().handle;

    var UserDetailQuery = $resource('/api/account/show/:query');
    var loggedInUser = UserDetailQuery.get({query: currentHandle});

    // need to
    // 1) verify that there are followers and
    // 2) if there are, loop through and see if currentUser is one of them
    var FollowersQuery = $resource('/api/account/following/:query', {query:loggedInUser.Id});
    var followers  = FollowersQuery.query(); //get({handle:$location.$$search.q});
    //http://localhost:8080/api/account/following/1

    // 3) change the flag to true if I am following
    // 4) if false then clicking on it should make the loggedInUser follow the
    //    user displayed on the user detail page
    $scope.iAmFollowing = false;

    $scope.loggedInUser = loggedInUser;
    $scope.user = UserDetailQuery.get({query: handleQuery});

    var MessageQuery = $resource('/api/message/accnt/:query', {query:handleQuery});
    $scope.messages  = MessageQuery.query(); //get({handle:$location.$$search.q});
}]);