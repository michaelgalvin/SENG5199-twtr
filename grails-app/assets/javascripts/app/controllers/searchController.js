/**
 * Created by cruzr004 on 4/2/16.
 *
 *
 * S1: Provide a search box for finding messages by message poster and message contents
 * S2: Display matching results in a scrollable area below the search box
 * S3: Search result messages will display the message contents as well as the posting user.
 * S4: Clicking on the posting user’s name in a message will route to the user’s detail page
 *
 */

// Define a controller called 'welcomeController'
angular.module('app').controller('searchController', function($scope) {
    $scope.searchGreeting = 'Enter search parameters'
});
