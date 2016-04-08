/**
 * Created by cruzr004 on 4/2/16.
 *
 *
 *
 */

// Define a controller called 'searchController'
angular.module('app').controller('searchController', function ($scope, authenticationService) {

    $scope.searchGreeting = 'Enter search parameters'

    //  S1: Provide a search box for finding messages by message poster and message contents
    //      on submit send Ajax query to the MessageController.search method

    //  S2: Display matching results in a scrollable area below the search box
    //      get results
    var getSearchMessageData = function () {
        return [
            // $scope.messages = [
            // {author: 'User', text: 'Warble'},
            {author: {handle: 'groovyNewbie'}, text: 'John and Jane very intelligent?'},
            {author: {handle: 'groovyNewbie2'}, text: 'Beverly\'s boyfriend  almost always counts money at 4:51.'},
            {author: {handle: 'groovyNewbie2'}, text: 'Ms. Smith  digs a hole in the mountains.'},
            {author: {handle: 'Nerd2'}, text: 'I have studied English twice.'},
            {author: {handle: 'Posse2'}, text: 'Scott\'s wife  is washing clothes.'},
            {author: {handle: 'PossessWeekly2'}, text: 'Please dance at school.'},
            {author: {handle: 'PossessWeekly2'}, text: 'Frank  buys milk in the mountains.'},
            {author: {handle: 'TheNerd2'}, text: 'Please watch TV in front of the bank.'}
        ];
        //      loop through results and display them in a table

        //  S3: Search result messages will display the message contents as well as the posting user.
        //      message.author.handle, message.text

        //  S4: Clicking on the posting user’s name in a message will route to the user’s detail page
        //      when rendering message.author.handle wrap in link to detail page

    };
    $scope.messages = getSearchMessageData();
});
