<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 29.01.2018
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="https://cdn.polyfill.io/v1/polyfill.js?features=Element.prototype.closest"></script>
    <script src="/resources/js/DragManager.js"></script>
    <script>
        DragManager.onDragCancel = function(dragObject) {
            dragObject.avatar.rollback();
        };

        DragManager.onDragEnd = function(dragObject, dropElem) {
            var id=dragObject.elem.id;
            doDeletePost(id, dragObject);
            dragObject.elem.style.display = 'none';
            /*dropElem.classList.add('computer-smile');
            setTimeout(function() {
                dropElem.classList.remove('computer-smile');
            }, 200);*/
        };
    </script>
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
    .outer{

        width: 100%;
        background-color: #a4a4a4;
        height: 900px;
        position: relative;
    }

    .top3new{
        position: absolute;
        bottom: 5%;
        height: 30%;
        width: 26%;
    }

    .inner1{

        left: 5%;

    }

    .inner2{

        left: 37%;
    }
    .inner3{

        right: 5%;

    }

    .top4right{
        position: absolute;
        right: 5%;
        width: 35%;
        height: 3%;
        font-size: 18px;

    }

    .inner4{
        top: 30%;

    }
    .inner5{

        top: 36%;

    }

    .inner6{

        top: 41%;

    }

    .inner7{
        top: 47%;

    }
</style>
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

    #mytrashbucket {
        display: none;
        position: fixed;
        right: 20px;
        bottom: 20px;
        width: 100px;
        z-index: 99;
    }

    #myBtn:hover {
        background-color: #555;
    }

    .avatar{
        display: block;
        width: 20px;
        height: 20px;
        background-color: #5bc0de;
    }

    .carddiv{
        display: inline-block;
        vertical-align: top;
        height: 300px;
        margin: 3px;
        border: 1px solid darkblue;

    }

    .artDiv{
        background-color: #e6f1ff;
        margin-bottom: 5px;
    }
</style>
<body>

<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
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
            <button data-toggle="collapse" data-target="#demo">Дата</button>
            <div id="demo" class="collapse">
                <sf:form modelAttribute="datesearch" method="post">

                    <sf:input path="date" id="datepicker"/>
                    <input type="submit">


                </sf:form>

            </div>

            <c:choose>
                <c:when test="${isSevenPostsExist}">
            <div class="outer ">

                <div style="position: absolute; top: 5%; font-size: 75px">УЮТНЫЙ ДРАКОН</div>
                <div style="position: absolute; top: 18%; font-size:35px; left: 5%"><fmt:formatDate value="${cardList.get(0).date}" type="date" pattern="dd-MM-yyyy"/></div>

                <a href="/article/${cardList.get(0).id}">
                <div class="inner1 top3new">
                    <div class="card">
                        <img class="card-img-top" style="width: 100%" src="/resources/imgs/1.png" alt="Card image cap">
                        <div class="card-block">
                            <h4 class="card-title">${cardList.get(0).name}</h4>
                            <p class="card-text">${cardList.get(0).content}</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </div>
                </a>
                <a href="/article/${cardList.get(1).id}">
                <div class="inner2 top3new"><div class="card">
                    <img class="card-img-top" style="width: 100%" src="/resources/imgs/1.png" alt="Card image cap">
                    <div class="card-block">
                        <h4 class="card-title">${cardList.get(1).name}</h4>
                        <p class="card-text">${cardList.get(1).content}</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div></div>
                </a>
                    <a href="/article/${cardList.get(2).id}">
                <div class="inner3 top3new"><div class="card">
                    <img class="card-img-top" style="width: 100%" src="/resources/imgs/1.png" alt="Card image cap">
                    <div class="card-block">
                        <h4 class="card-title">${cardList.get(2).name}</h4>
                        <p class="card-text">${cardList.get(2).content}</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div></div>
                    </a>
                <a href="/article/${cardList.get(4).id}">
                <div class="inner4 top4right">${cardList.get(4).name}</div>
                </a>
                    <a href="/article/${cardList.get(5).id}">
                    <div class="inner5 top4right">${cardList.get(5).name}</div>
                    </a>
                        <a href="/article/${cardList.get(6).id}">
                <div class="inner6 top4right">${cardList.get(6).name}</div>
                        </a>
                            <a href="/article/${cardList.get(7).id}">
                <div class="inner7 top4right">${cardList.get(7).name}</div>
                            </a>
            </div>
                </c:when>
                <c:otherwise>
                    Извините, постов за указанную не хватает для создания журнала. Вы будете переадресованы на список постов.
                    <script>

                        setTimeout(function () {
                            var str="${dateStr}";
                            window.location="${pageContext.request.contextPath}/feed/rated?date="+str;
                        }, 3000)
                    </script>

                </c:otherwise>
            </c:choose>

        </div>
        <div class="col-lg-4">
            <div class="row">
                <div class="col-lg-12">
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

                        <div class="list-group">
                            <sec:authorize access="hasRole('ROLE_ADMIN')"><button type="button" class="list-group-item list-group-item-action" id="adminbutton" onclick="adminUI()">Admin Mode On</button></sec:authorize>
                            <button type="button" class="list-group-item list-group-item-action" id="viewbutton" onclick="showCardView()">Card View</button>
                            <a href="#" class="list-group-item list-group-item-action">Default List Group item </a>
                            <a class="list-group-item list-group-item-action"><span class="badge">New</span>List Group item 1 with badge</a>
                        </div>
                    </sec:authorize>
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

<script type="text/javascript">
    <c:forEach items="${cardList}" var="card">
    $('<div>', {
        "class": 'card carddiv draggable',
        id: 'carddiv'+${card.id},
        style: 'width: 32%'
    }).appendTo('#postcontainer').hide();
    $('<img>', {
        "class":'card-img-top',
        src: '/resources/imgs/1.png',
        width: '100%'

    }).appendTo('#carddiv'+${card.id});

    $('<div>', {
        "class": 'card-body',
        id: 'cardbdiv'+${card.id}

    }).appendTo('#carddiv'+${card.id});

    $('<h5>', {
        "class": 'card-title',
        text: '<c:out value="${card.name}"/> '

    }).appendTo('#cardbdiv'+${card.id});

    $('<p>', {
        "class": 'card-text',
        text: '<c:out value="${card.content}"/> '

    }).appendTo('#cardbdiv'+${card.id});

    </c:forEach>

</script>

<script type="text/javascript">
    /*    function doCardView() {

            var ids='';
            $(".artDiv").hide();

            $.ajax({

                type: 'get',
                contentType: 'application/json',
                mimeType: 'application/json',
                data:({

                    ids: ids
                }),
                success: function (cardata) {
                    for (var i=0; i<elems.length; i++){
                        elems[i].hidden=true;
                    }
                    var length=cardata.cardDtoList.length;
                    for (var ii=0; ii<cardata.cardDtoList.length; ii++){







                    }


                    $("#viewbutton").click(doBlockView).text("BlockView");
                }
            })

        }*/

    function doBlockView() {
        $(".carddiv").hide();
        $(".artDiv").show();
        $("#viewbutton").click(showCardView).text("CardView");

    }

    function showCardView() {
        $(".artDiv").hide();
        $(".carddiv").show();
        $("#viewbutton").click(doBlockView).text("BlockView");
    }

</script>

<script>
    var a=$(".inner4").height();
    var fs=a-a/10;
    $(".top4right").style("font-size", fs+"px");

</script>

<script type="text/javascript">
    function doDeletePost(id, dragObject) {
        $.ajax({url: '${pageContext.request.contextPath}/article/delete',
            type: 'get',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data : ({
                id: id

            }),
            success: function () {
                dragObject.elem.display='none';
            }

        })
    }
</script>

<script type="text/javascript">

    function SparkWrapper(id){
        var wrapper=document.getElementById('wrapper'+id);
        wrapper.style.backgroundColor='red';
    }

    function doWrap(id) {
        var divId='content'+id+' ';
        var divToHide=document.getElementById(divId);
        if(divToHide.hidden){
            divToHide.hidden=false;
        }
        else{
            divToHide.hidden=true;
        }
    }

</script>

<script type="text/javascript">
    function doDecAjax(id, val, user) {

        $.ajax({
                url: '${pageContext.request.contextPath}/article/UPComment',
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



<script type="text/javascript">
    function adminUI() {


        var elems=document.getElementsByClassName('artDiv');
        for(var i=0; i< elems.length; i++){
            var elem=elems[i];
            elem.className+=' draggable';
        }
        document.getElementById('adminbutton').onclick=notAdminUI;
        document.getElementById('adminbutton').innerHTML='Admin Mode OFF';
        document.getElementById('mytrashbucket').style.display="block";
    }

    function notAdminUI() {
        var elems=document.getElementsByClassName('artDiv');
        for(var i=0; i< elems.length; i++){
            var elem=elems[i];
            elem.classList.remove('draggable');
        }
        document.getElementById('adminbutton').onclick=adminUI;
        document.getElementById('adminbutton').innerHTML='Admin Mode ON';
        document.getElementById('mytrashbucket').style.display="none";
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
