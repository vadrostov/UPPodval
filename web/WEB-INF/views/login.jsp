<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 23.01.2018
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>

    <script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/resources/js/jquery-1.11.3.min.js"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/bootstrap.js"></script>
</head>

<style>
    #myBtn {
        display: none;
        position: fixed;
        bottom: 20px;
        left: 20px;
        z-index: 99;
        border: none;
        outline: none;
        background-color: red;
        color: white;
        cursor: pointer;
        padding: 15px;
        border-radius: 10px;
    }

    .mytrashbucket {
        display: block;
        background:url(https://findicons.com/files/icons/1580/devine_icons_part_2/128/trash_recyclebin_empty_closed.png) no-repeat;
        position: fixed;
        top: 20px;
        left: 20px;

    }

    #myBtn:hover {
        background-color: #555;
    }

    .artDiv{
        background-color: rgba(228, 250, 255, 0);
    }
</style>

<body>
<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
<button id="mytrashbucket" class="dropable mytrashbucket"></button>

<nav class="navbar navbar-default">
    <div class="container-fluid">


        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#defaultNavbar1" aria-expanded="false"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
            <a class="navbar-brand" href="#">УЮтП</a></div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="defaultNavbar1">
            <ul class="nav navbar-nav">

                <li class="dropdown"><a href="/feed/hot" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Лента<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/feed/new">Новое</a></li>
                        <li><a href="/feed/hot">Актуальное</a></li>
                        <li><a href="/feed/rated">Лучшее</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a href="/feed/hot" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Блоги<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/feed/new">Новое</a></li>
                        <li><a href="/feed/hot">Актуальное</a></li>
                        <li><a href="/feed/rated">Лучшее</a></li>
                    </ul>
                </li>
                <li><a href="https://www.youtube.com/channel/UCeemTTAKVjHucKYFmc8V3Ug">Ютуб</a></li>
            </ul>
            <ul class="nav nav-inline navbar-right">
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <div class="artDiv">
                    <div class="row">
                        <div class="col-lg-12" align="center">
                            <img src="/resources/imgs/login/no.png" id="imagelogin">
                            <div class="imagechange" id="changeImage"></div>
                            <form>

                                <input id="login" onfocus="inputImage()" oninput="inputChange()" type="text"><br>
                                <input id="pass" onfocus="passChange()" type="password">
                            </form>

                        </div>
                    </div>



            </div>
        </div>
        <div class="col-lg-4">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <button type="button" onclick="location.href='/user/registration';" class="btn btn-secondary btn-block btn-sm">Регистрация</button>
                <form name='f' method='POST'
                      action='/j_spring_security_check'>

                    <div>

                        <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Логин" aria-describedby="addon1">
                    </div>

                    <div >
                        <input type="password" id="j_password" name="j_password" class="form-control" placeholder="Пароль" aria-describedby="addon1">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block btn-sm">Войти</button>
                </form>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_USER')">

                <ul class="list-group">
                    <li class="list-group-item">Default List Group item </li>
                    <li class="list-group-item"><span class="badge">New</span>List Group item 1 with badge</li>
                </ul>
            </sec:authorize>
        </div>


    </div>
</div>




<script>

    function inputImage() {
        var text=$("#login").val();
        if (text.length>0 &&text.length<12){
            var count='/resources/imgs/login/'+text.length+'.png';
            $("#imagelogin").attr("src",count);
        }
        else if (text.length>12){
            $("#imagelogin").attr("src","/resources/imgs/login/12.png");
        }
        else  $("#imagelogin").attr("src","/resources/imgs/login/1.png");
    }

    function inputChange() {
        var text=$("#login").val();
        if (text.length<12&&text.length>0){
            var count='/resources/imgs/login/'+text.length+'.png';
            $("#imagelogin").attr("src",count);
        }
        else if (text.length>=12){
            $("#imagelogin").attr("src","/resources/imgs/login/12.png");

        }
        else $("#imagelogin").attr("src","/resources/imgs/login/1.png");

    }
</script>

<script>
    function passChange() {
        $("#imagelogin").attr("src","/resources/imgs/login/n.png");


    }
</script>








</body>
</html>