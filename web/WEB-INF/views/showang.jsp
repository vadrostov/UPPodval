<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 08.05.2018
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="app">
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular.min.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>

    <script src="/resources/js/angularmain.js"></script>

    <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>



</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-8">


            <div class="row"  id="postcontainer">

                <div ng-controller="appCtrl" id="appdiv">



                </div>


            </div>
        </div>
        <div class="col-lg-4">
            <div class="row">
                <div class="col-lg-12" id="artdiv">

                    <div ng-controller="secCtrl" id="secdiv">



                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div id="mytrashbucket" class="droppable mytrashbucket" hidden><img src="https://api.icons8.com/download/6eabcedee867ff3db7d71502698b147f88b8b7b0/windows8/PNG/512/Editing/delete-512.png" width="100%"/></div>
                </div>
            </div>
        </div>


    </div>
</div>



</body>
</html>
