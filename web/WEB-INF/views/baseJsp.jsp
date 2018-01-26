<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 23.01.2018
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#defaultNavbar1" aria-expanded="false"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
            <a class="navbar-brand" href="#">УЮтП</a></div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="defaultNavbar1">
            <ul class="nav navbar-nav">

                <li><a href="#">Лента</a></li>
                <li><a href="#">Блог</a></li>
                <li><a href="#">Ютуб</a></li>
                <li><a href="#">Смешнявки</a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Выпадайка<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Мои посты</a></li>
                        <li><a href="#">Мои комментарии</a></li>
                        <li><a href="#">Сохраненные</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Выйти</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<div class="row">
    <div class="col-md-2 col-lg-1"></div>
    <div class="col-md-5 col-lg-6">

    </div>
    <div class="col-md-1"></div>
    <div class="col-md-3">
        <div class="row">
            <div class="col-lg-9 col-md-10"><strong><c:out value="${username}"/> </strong></div>
            <div class="col-lg-1"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></div>
        </div>
        <a href="#"> Мои посты</a><br>

        <a href="#">Мои комментарии</a><br>
        <a href="#">Сохраненное</a><br>


        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <form name='f' method='POST'
                  action='/j_spring_security_check'>
                <label for="j_username">Username: </label>
                <input id="j_username" name="j_username" size="20" maxlength="50" type="text" /><br/>

                <label for="j_password">Password: </label>
                <input id="j_password" name="j_password" size="20" maxlength="50" type="password" /><br/>
                <input type="submit" class="button button1" value="Login" />
            </form>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
            <a href="/user/${username}"><c:out value="${username}"/> </a>
        </sec:authorize>

    </div>

</div>

<script type="text/javascript">
    function doDecAjax(id, val, user) {

        $.ajax({
                url: '${pageContext.request.contextPath}/article/comment',
                type: 'get',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data : ({
                    text: id,
                    val: val,
                    username: user

                }),
                success: function (data) {

                    var result=data.rate;
                    var obj='#commentRate'+data.id;
                    $(obj).text(result);
                }

            }


        )
    }

</script>

<script>
    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function() {scrollFunction()};

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            document.getElementById("myBtn").style.display = "block";
        } else {
            document.getElementById("myBtn").style.display = "none";
        }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }
</script>


</body>
</html>
