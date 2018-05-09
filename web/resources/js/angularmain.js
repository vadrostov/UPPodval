
var app=angular.module('app', []);

app.controller('appCtrl', function ($scope, $http) {


    function showFeed(data) {
        $scope.articlee=null;
        $scope.comments=null;
        $scope.articles = data;
    }

    $http.get('/angfeed').success(function(data) {
       showFeed(data);
    });
    

    $scope.showfeed=function () {
        $http.get('/angfeed').success(function(data) {
            showFeed(data);
        });
    }

    $scope.openArticle = function () {
        var id=this.article.id;

        var config = {
            params: {
                id: id
            }
        }
        $http.get('/angart', config).success(function (data) {
            $scope.articles=null;
            $scope.articlee=data;
            $scope.comments=data.commentDtoList;

        })

    }

}



)

app.directive('article', function () {
    return {
        templateUrl: "/resources/html/article1template.html",
    }
})

app.directive('articlecomments', function () {
    return{
        templateUrl: "/resources/html/articlecommenttemplate.html"
    }
})

app.directive('artlist', function () {
    return {
        templateUrl: "/resources/html/articletemplate.html",
        link: function ($scope, element, attrs) {
        }
    }
});