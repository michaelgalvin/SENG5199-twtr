//describe("true", function() {
//    it("Should be true", function(){
//        expect(false).toBeTruthy()
//
//    });
//
//});


describe("messageController", function () {
    beforeEach(module('app'));

    var $httpBackend, $scope, $controller, securityService;
    var currentUser = undefined;
    var gn = '{"username": "groovyNewbie","roles": ["ROLE_READ"],"access_token": "ldlggjvnnktbhv06s810tr7ss9b26m4l"}';
    var securityService = {
        currentUser: function () {
            return currentUser;
        },

        setCurrentUser: function () {
            return currentUser;
        },

        loginSucess: function () {
            return gn;
        }
    };


    beforeEach(inject(function ($injector) {
        $controller = $injector.get('$controller');
        $httpBackend = $injector.get('$httpBackend');
        // securityService = $injector.get('securityService');
        $scope = $injector.get('$rootScope').$new();

    }));
    describe("Message Posting", function () {

        beforeEach((function () {
            currentUser = {token: 'xyz', role: 'admin', username: 'eddie'};
            spyOn(securityService, 'currentUser').and.returnValue(currentUser);
            $scope.twtrform = {twtrcheck: {$valid: true}};
            $controller('messageController', {$scope: $scope, securityService: securityService});

        }));

        it('should post message', function () {

            $httpBackend.when('POST', '/api/message', {
                message: {
                    text: "Tweeting from Karma",
                    author: {handle: "eddie"}
                }
            }).respond(201, {author: {handle: 'eddie'}});
            $scope.text = 'Tweeting from Karma';
            $scope.postMessage();
            $httpBackend.flush();
        });

    });

});


