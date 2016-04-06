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