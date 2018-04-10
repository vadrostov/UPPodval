<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 20.02.2018
  Time: 13:49
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
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value="/resources/js/jquery-1.11.3.min.js"/>"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/resources/js/bootstrap.js"/>"></script>

    <script src="<c:url value="/resources/js/jquery-labelauty.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-labelauty.css"/>">

    <script>
        $(document).ready(function(){
            $(".jquery-checkbox-label").labelauty({ minimum_width: "155px" });
            $(".to-labelauty-icon").labelauty({ label: false });
        });
    </script>
</head>
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

<div class="separator"></div>

<sf:form modelAttribute="createdArticle">
    <input type="hidden" name="_flowExecutionKey"
           value="${flowExecutionKey}"/>
<ul id="lby-checkbox-demo">
    <c:forEach items="${createdArticle.categoryDtos}" var="dtos" varStatus="s">

        <form:checkbox class="jquery-checkbox-label terms-icon" path="categoryDtos[${s.index}].checked" data-labelauty="${dtos.name}"  />
    </c:forEach>
</ul>
    <input type="submit" name="_eventId_done" class="btn btn-success" value="Next">
</sf:form>

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

<%--<script type="text/javascript">
    function addCategory(e) {
        var catid=e;
        var catid2=e+'1';
        $("#"+catid).hide();
        $("#"+catid2).show();

        $("#catHidden").val($("#catHidden").val()+catid+' ');
    }

    function removeCategory(e) {
        var cat=e;
        var catid=cat.trim();
        var catid2=catid+'1';
        var removed=$("#catHidden").val().replace(cat, '');
        $("#catHidden").val(removed);
        $("#"+catid).show();
        $("#"+catid2).hide();
            }

</script>

<script>
    $(".cat-rem").hide();
</script>--%>

</body>
</html>
