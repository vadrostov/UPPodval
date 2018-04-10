<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 29.01.2018
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


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
    .artDiv{
        background-color: #e6f1ff;
    }

</style>
<body>
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
            <sf:form method="post" modelAttribute="user" action="/user/registration">

                <div class="form-group row">
                    <label for="login-input" class="col-xs-2 col-form-label">Логин</label>
                    <div class="col-xs-10">
                        <sf:input class="form-control" path="login" type="text" id="login-input" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="example-search-input" class="col-xs-2 col-form-label">E-mail</label>
                    <div class="col-xs-10">
                        <div class="input-group">
                        <sf:input class="form-control" path="email" type="search" id="example-search-input"/>
                            <span class="input-group-addon" id="basic-addon2">example@example.com</span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="example-email-input" class="col-xs-2 col-form-label">Пароль</label>
                    <div class="col-xs-10">
                        <div class="input-group">
                        <sf:input path="password" class="form-control" type="password"  id="example-email-input"/>
                            <span class="input-group-addon" id="basic-addon2">Минимум 8 знаков</span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="example-url-input" class="col-xs-2 col-form-label">Повторите пароль</label>
                    <div class="col-xs-10">
                        <div class="input-group">
                        <sf:input path="matchingPassword" class="form-control" type="password"  id="example-url-input"/>
                            <span class="input-group-addon" id="basic-addon2">Минимум 8 знаков</span>
                        </div>
                    </div>
                </div>
                <input type="submit">

            </sf:form>
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
</body>
</html>
