/**
 * Created by galvi024 on 4/3/16.
 */
app.factory('authenticationService', function(){
    var user;

    return{
        setUser : function(aUser){
            user = aUser;
        },
        isLoggedIn : function(){
            return(user)? user : false;
        }
    }
});
// app.service('authenticationService', function() {
//     var username = {};
//     var token = {};
//
//     var getUsername = function() {
//         return username
//     };
//     var getToken = function() {
//         return token;
//     };
//
//     var setUsername = function(name) {
//         username = name;
//     };
//     var setToken = function(tkn) {
//         token = tkn;
//     };
//
//     // return getToken, getUsername, setUsername(), setToken();
//     return {
//
//         getToken : getToken,
//         setToken : setToken,
//         getUsername : getUsername,
//         setUsername: setUsername
//
//         /*addAttendee: addAttendee,
//          getAttendees: getAttendees,
//          updateAttendee: updateAttendee,
//          deleteAttendee: deleteAttendee*/
//     };
// });