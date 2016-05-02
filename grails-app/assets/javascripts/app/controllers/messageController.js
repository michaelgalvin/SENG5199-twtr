angular.module('app').controller('messageController', ['$scope', '$location', '$resource', 'securityService', '$routeParams', function ($scope, $location, $resource, securityService, $routeParams) {

    var maxPostLength = 128;
    $('#twtr-text').attr('maxLength',maxPostLength);

    $scope.alerts = [];

    var alerts = {
        error: {
            type: 'danger',
            msg: 'Ah snap! Change a few things up and try submitting again.'
        },
        success: {
            type: 'success',
            msg: 'Message Posted!'
        }
    };

    $scope.addAlert = function(alert) {
        $scope.alerts.push(alert);
    };

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

    $scope.resetAlerts = function() {
        $scope.alerts.splice(0, $scope.alerts.length);
    };

    var user = securityService.currentUser();
    var handle = user.username;

    var Message = $resource('/api/message/:messageId',

        {messageId: '@id'}, {

            post: {
                method: 'POST',
                params: {
                    message: {
                        text: $scope.text,
                        author: { handle: handle }
                    }
                },
                headers: {'X-Auth-Token': user.token}
            }
        }
    );

    $scope.keypress = function($event) {

        var text_length = $('#twtr-text').val().length;
        var text_remaining = maxPostLength - text_length;

        if (text_remaining === 0) {
            $scope.resetAlerts();
            $scope.addAlert(alerts.error);
            var text = $('#twtr-text').val();
            $('#twtr-text').val(text.substring(0,text.length-1));
        }

        $('#char_count').html('(' + text_remaining + '/' + maxPostLength + ' remaining)');
    };

    $scope.postMessage = function() {
        var post = $scope.text;

        Message
            .save({
                message: {
                    text: post,
                    author: { handle: handle }
                }})
            .$promise.then(
                function (data) {
                    var postedMessage = data;
                    var handle = postedMessage.author.handle;
                    $('#twtr-text').val('');
                    $scope.resetAlerts();
                    $scope.addAlert(alerts.success);
                    console.log("message posted successfully by " + handle);
                },
                function (failed) {
                    $scope.resetAlerts();
                    $scope.addAlert(alerts.error);
                }
            );
    };



}]);