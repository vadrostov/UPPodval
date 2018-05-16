
var app=angular.module('app', []);


app.controller('secCtrl', function ($scope, $http, $compile, rolesfactory) {


    $scope.rolesfactory=rolesfactory;
    var authtoken=null;

    checkauth();

    function checkauth() {
        $http.get('/angsec').success(function (data) {
            authtoken=data;
            var userName=data.name;
            rolesfactory.token=data;
            if (!rolesfactory.isAuth()){
                angular.element($('#secdiv')).empty();
                var elem=$compile("<secneed></secneed>")($scope);
                angular.element($('#secdiv')).append(elem);
            }
            else {
                $('#secdiv').empty();

                var elem=$compile("<secok></secok>")($scope);
                angular.element($('#secdiv')).append(elem);

            }
        });
    }



    $scope.auth=function () {
        var $form=$('#loginform');

        $.ajax({
            url: '/j_spring_security_check',
            type: 'POST',
            data: $form.serialize(),
            success: function(result) {
                checkauth();
            },
            error: function () {
                $('#loginform').append($('<p>Неверные данные</p>'));
            }
        })
    }




});

app.directive('secok', function () {
    return{
        templateUrl: "/resources/html/sidemenu.html"
    }
});

app.directive('secneed', function () {
    return{
        templateUrl: "/resources/html/loginform.html"
    }
});

app.controller('appCtrl', function ($scope, $http, $compile, rolesfactory) {

    $scope.rolesfactory=rolesfactory;


    function showFeed(data) {
        $scope.articlee=null;
        $scope.comments=null;
        $scope.articles = data;
        $('#appdiv').empty();
        var elemartfeed=$compile("<artlist></artlist>")($scope);
        angular.element($('#appdiv')).append(elemartfeed);

    }

    $http.get('/angfeed').success(function(data) {
       showFeed(data);
    });


    $scope.showuser=function () {
        var user = this.article.author;

        $http.get('/anguser', {params:{username: user}}).success(function (data) {
            $('#appdiv').empty();

            $scope.articlee=null;
            $scope.comments=null;
            $scope.articles = null;
            $scope.userdata=data;
            var elemuser=$compile("<userinfo></userinfo>")($scope);
            angular.element($('#appdiv')).append(elemuser);
            if (rolesfactory.isAdmin()){


                $scope.roles=data.roles;

                var elemuseradmin=$compile("<userinfoadmin></userinfoadmin>")($scope);
                angular.element($('#appdiv')).append(elemuseradmin);
            }

        })
    };
    
    $scope.submituserroles=function () {
        var data=1;
    }

    $scope.showfeed=function () {
        $http.get('/angfeed').success(function(data) {
            showFeed(data);
        });
    }

    $scope.openArticle = function () {
        var id=this.article.id;

        var config = {

                id: id
            }

        $http.get('/angart', config).success(function (data) {
            $scope.articles=null;
            $('#appdiv').empty();
            $scope.articlee=data;
            $scope.comments=data.commentDtoList;
            var elemart=$compile("<article></article>")($scope);
            var elemcom=$compile("<articlecomments></articlecomments>")($scope);
            angular.element($('#appdiv')).append(elemart);
            angular.element($('#appdiv')).append(elemcom);

        })

    }

}



)

app.directive('article', function () {
    return {
        templateUrl: "/resources/html/article1template.html"
    }
})

app.directive('articlecomments', function () {
    return{
        templateUrl: "/resources/html/articlecommenttemplate.html"
    }
})

app.directive('userinfo', function () {

    return{
        templateUrl: "/resources/html/usertemplate.html"

    }
});

app.directive('userinfoadmin', function () {
    return{
        templateUrl: "/resources/html/useradmintemplate.html"
    }
})

app.directive('artlist', function () {
    return {
        templateUrl: "/resources/html/articletemplate.html",
        link: function ($scope, element, attrs) {
        }
    }
});

app.factory('rolesfactory', function () {

    return {
        token: {},

        isAdmin: function () {
            for (var i=0; i<this.token.authorities.length; i++){
                if (this.token.authorities[i].authority=='ROLE_ADMIN'){
                    return true
                }

            }
            return false
        },

        isAuth: function () {
            for (var i=0; i<this.token.authorities.length; i++){
                if (this.token.authorities[i].authority=='ROLE_USER'){
                    return true
                }

            }
            return false
        }
    }

})