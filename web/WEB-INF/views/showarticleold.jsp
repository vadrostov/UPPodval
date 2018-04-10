<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 15.01.2018
  Time: 16:10
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

    #myBtn:hover {
        background-color: #555;
    }

    .but3 {border-width:8px 0 8px 8px; height:0;}
    .but3 {border-style:solid; border-color:transparent transparent transparent blue;}

    .but5 {float:left;}
    .but5 {border-width:8px 8px 8px 0; height:0;}
    .but5 {border-style:solid; border-color:transparent blue transparent transparent;}
</style>

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
        <div class="row">
            <div class="col-md-2">
                <div class="row">
                    <div class="col-lg-4"><input type="button" id="but"  value="${article.id}" onclick="doDecAjax(${article.id}, 'dec', '${username}')" class="but5"></input></div>
                    <div  class="col-lg-4"><p id="commentRate"> <strong><c:out value="${article.rate}"/></strong></p></div>
                    <div class="col-lg-4"><input type="button"  value="${article.id}" onclick="doDecAjax(${article.id}, 'inc', '${username}')" class="but3"></div>
                </div>

            </div>
            <div class="col-md-8 col-lg-10"><a href="#"><strong><c:out value="${article.name}"/></strong></a></div>
        </div>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-3"><c:out value="${article.date}"/> </div>
            <div class="col-md-4 col-lg-4">Автор: ${article.author}</div>
            <div class="col-md-1 col-lg-offset-2"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> </div>
        </div>
        <div class="row" style="background-color: lavender">
            <div class="col-md-2">
                <div class="row">
                    <div class="col-lg-4"></div>
                    <div  class="col-lg-4"></div>
                    <div class="col-lg-4"></div>

                </div>

            </div>
            <div class="col-md-8 col-lg-10">
                <c:forEach items="${article.tags}" var="tag">
                    <a href="/feed/tag?tagname=${tag.name}"><span class="label label-warning"><c:out value="${tag.name}"/></span></a>
                </c:forEach>
            </div>
        </div>
        <div class="row">

            <div class="col-md-10 col-lg-12"><c:out escapeXml="false" value="${article.content}"/>
        </div>
        <div class="row">
            <div class="col-md-12"><a href="#" >Комментарии: 667</a></div>
        </div>
            <c:forEach items="${UPComments}" var="UPComment">
                <c:if test="${UPComment.parrentcomment}=0"
                <br/>
                <c:out value="${UPComment.content}"/>
                <br/>
            </c:forEach>
        </br>
            <sf:form method="post" modelAttribute="createdComment" >

                <sf:textarea path="content" id="text" />
                <input type="submit" href="/UPComment/add"/>

            </sf:form>
            </br>

    </div>
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


    </div>

</div>

<script type="text/javascript">
    function doDecAjax(id, val, user) {

        $.ajax({
            url: 'UPComment',
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
                $("#commentRate").text(result);
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
