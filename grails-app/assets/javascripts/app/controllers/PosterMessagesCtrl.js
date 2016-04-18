/**
 * Created by cruzr004 on 4/10/16.
 */
angular.module('app').controller('PosterMessagesCtrl', ['$scope','$location', '$resource', function ($scope, $location, $resource) {
    //  S1-partA: Provide a search box for finding messages by message poster
    var MessageQuery = $resource('/api/message/accnt/:handle', {handle:$location.$$search.q});
    $scope.messages = MessageQuery.query(); //get({handle:$location.$$search.q});
}]);