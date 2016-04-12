angular.module('app').controller('logoutController', function(securityService) {
    securityService.logoutUser()
});