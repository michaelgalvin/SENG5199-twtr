/**
 * Created by cruzr004 on 4/10/16.
 */
angular.module('app').controller('GrepMessagesCtrl', ['$scope','$location', '$resource', function ($scope, $location, $resource) {
        //  S1-partA: Provide a search box for finding messages by message poster
        var MessageQuery = $resource('/api/message/search?q=:query', {query:$location.$$search.q});

        $scope.messages = MessageQuery.query(); //get({handle:$location.$$search.q});
}]);