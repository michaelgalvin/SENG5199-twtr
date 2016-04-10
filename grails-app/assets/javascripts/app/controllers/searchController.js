angular.module('app').controller('searchController', ['$scope', '$location', function ($scope, $location) {

    // var Restaurant = $resource('/api/account/:account_id', {restaurantId: '@id'});

    // $scope.restaurants = Restaurant.query();

    $scope.searchGreeting = 'Enter search parameters';

    $scope.searchByHandle = function() {
        //"/api/message"(resources: "message")

        $location.url('/posterMessages?q=' + encodeURIComponent($scope.handle));
    };

    //  S4: Clicking on the posting user’s name in a message will route to the user’s detail page
    //      when rendering message.author.handle wrap in link to detail page


}]);