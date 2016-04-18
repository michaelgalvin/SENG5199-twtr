angular.module('app').controller('logoutController', function ($scope, securityService) {
    securityService.logoutUser()
    $scope.logout = "He's dead, Jim."
});