angular.module('app').directive('followDirective', function () {
    return {
        template:
        '<button type="button" id="follow" class="btn btn-lg btn-primary btn-block" ng-click="followUser()"> {{toFollow}} </button>'
    };
});