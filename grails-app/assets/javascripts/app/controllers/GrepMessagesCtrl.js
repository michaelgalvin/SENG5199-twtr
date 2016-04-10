/**
 * Created by cruzr004 on 4/10/16.
 */
angular.module('app').controller('GrepMessagesCtrl', ['$scope', '$resource', function ($scope, $resource) {
        //  S1-partB: Provide a search box for finding messages by message contents
        //"/api/message/search", query: [q: 'Save', max: '2', offset: '5'])

        $scope.messages = $resource('/api/message/search?q=' + encodeURIComponent($scope.q));
}]);