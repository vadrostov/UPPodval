<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 29.01.2018
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>  
<head>
    <title>Title</title>

    <script src="/resources/js/upajax.js"></script>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>


    <script src="/resources/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <script src="/resources/js/commenttree.js"></script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>



    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/bootstrap.js"></script>



    <script type="text/javascript">
        function tree_toggle(event) {
            event = event || window.event
            var clickedElem = event.target || event.srcElement

            if (!hasClass(clickedElem, 'Expand')) {
                return // клик не там
            }

            // Node, на который кликнули
            var node = clickedElem.parentNode
            if (hasClass(node, 'ExpandLeaf')) {
                return // клик на листе
            }

            // определить новый класс для узла
            var newClass = hasClass(node, 'ExpandOpen') ? 'ExpandClosed' : 'ExpandOpen'
            // заменить текущий класс на newClass
            // регексп находит отдельно стоящий open|close и меняет на newClass
            var re =  /(^|\s)(ExpandOpen|ExpandClosed)(\s|$)/
            node.className = node.className.replace(re, '$1'+newClass+'$3')
        }


        function hasClass(elem, className) {
            return new RegExp("(^|\\s)"+className+"(\\s|$)").test(elem.className)
        }



    </script>
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
        background-color: #e6f1ff;
    }

    .UPComment-container {

        padding: 0;

        margin: 0;

    }



    .UPComment-container li {

        list-style-type: none;

    }

    .Node {
        margin-left: 18px;
    }

    .IsRoot {

        margin-left: 0;

    }

    .Expand {
        width: 18px;
        height: 18px;
        float: left;
    }
    .comment_content {

        border: 1px solid #727272;
        margin-left:18px;

        min-height: 18px;
    }

    .ExpandOpen .Expand {
        background-image: url(/resources/imgs/UPComment/ico/expand_minus.gif);
    }

    .ExpandClosed .Expand {
        background-image: url(/resources/imgs/UPComment/ico/expand_plus.gif);
    }

    .ExpandLeaf .Expand {
        background-image: url(/resources/imgs/UPComment/ico/expand_leaf.gif);
    }

    .Node {
        margin-left: 18px;
        zoom: 1;
        background-image : url(/resources/imgs/UPComment/ico/i.gif);
        background-position : top left;
        background-repeat : repeat-y;
    }


    .IsLast {
        background-image: url(/resources/imgs/UPComment/ico/i_half.gif);
        background-repeat : no-repeat;
    }

    .ExpandOpen .UPComment-container {
        display: block;
    }

    .ExpandClosed .UPComment-container {
        display: none;
    }

    .ExpandOpen .Expand, .ExpandClosed .Expand {
        cursor: pointer;
    }



    .ExpandLeaf .Expand {
        cursor: auto;
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
            <div class="container" id="artContainer">
                <div class="row">

                    <div class="row">
                        <div class="col-lg-2" align="center">
                            <button type="button" class="btn btn-sm btn-primary  btn-block" onclick="doDecAjax(${article.id}, 'inc', '${username}')">+</button>
                        </div>
                        <div class="col-lg-10">
                            <c:out value="${article.name}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2" align="center">
                            <c:out value="${article.rate}"/>
                        </div>
                        <div class="col-lg-10" >
                            <c:forEach items="${article.tags}" var="tag">
                                <a href="/feed/tag?tagname=${tag.name}" class="badge badge-primary"><c:out value="${tag.name}"/></a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2" align="center">
                            <button type="button" class="btn btn-sm btn-primary  btn-block" onclick="doDecAjax(${article.id}, 'dec', '${username}')">-</button>
                        </div>

                        <div class="col-lg-4">
                            Автор: ${article.author}
                        </div>

                        <div class="col-lg-4">
                            <c:out value="${article.date}"/>
                        </div>

                        <div class="col-lg-2">



                        </div>

                    </div>





                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <c:out escapeXml="false" value="${article.content}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">Комментарии: 667</div>
                    <div class="col-lg-8"></div>
                </div>




            </div>

            </div>
            <div class="row" id="commentDiv" onclick="tree_toggle(event)">
                <div class="col-lg-12" >
                    <ul id="UPComments" class="UPComment-container" data-post="${article.id}">


                    </ul>

                </div>
            </div>
            <div class="row">
                <div class="col-lg-12" >
                    <input id="commenttext00" type="text">
                    <input type="submit" data-parrentid="0" onclick="doAddComment()" <sec:authorize access="!hasRole('ROLE_USER')">disabled="disabled"</sec:authorize>>
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





<script type="text/javascript">
    <c:forEach items="${commenttree}" var="UPComment">

            var commentObj=new Comment('${UPComment.author}', ${UPComment.id}, '${UPComment.content}', ${UPComment.level}, ${UPComment.parent});
            var treeBuilder= new CommentTreeBuilder($('#UPComments'));
            treeBuilder.appendCommentToTree(commentObj);



    </c:forEach>


    $("#UPComments li:last-child").addClass('isLast');


   /*var c= jQuery.makeArray($('#UPComments .isRoot'));

    c.sort(function (a,b) {
        a=$(a).hasClass('isLast');
        b=$(b).hasClass('isLast');
        return a < b ? -1 : a > b ? 1 : 0

    });
    $(c).appendTo($('#UPComments')); */

    /*$('.ExpandOpen, .ExpandClosed').click(tree_toggle);*/


    function showCommentForm() {
        var e=event
        var id=event.target.id;
        var comdivid='#commcont'+id;
        var level=$(comdivid).attr('data-level');
        var bool=false
        <sec:authorize access="hasRole('ROLE_USER')">bool=true</sec:authorize>

        var exist=!!document.getElementById('commform'+id);
        if (!exist) {
            $(comdivid).append($('<div>', {id: 'commform' + id}).append(
                [
                    $('<input>', {id: 'commenttext' + id, type: 'text'}),

                    $('<input>', {
                        id: 'commentbut' + id,
                        type: 'submit',
                        'data-parrentid': id,
                        'data-level': level
                    }).click(doAddComment)

                ]
            ))
            if (!bool) {
                $('#commentbut' + id).attr('disabled', 'disabled');
            }
        }
        else $('#commform'+id).remove();
    }

</script>



<script type="text/javascript">
    function doAddComment() {

        var level;
        var commenttext;
        var pid;
        var parrentidis=$(event.target).attr('data-parrentid')
        if(parrentidis==='0'){
             level=0;
             commenttext=$('#commenttext00').val();
             pid='';

        }

        else {
             pid = $(event.target).attr('data-parrentid');
            var textid = 'commenttext' + $(event.target).attr('data-parrentid');
            var commentbutid='commentbut'+$(event.target).attr('data-parrentid');
             level = $(event.target).attr('data-level');
             commenttext = $('#' + textid).val();

        }
        var articleid = $('#UPComments').attr('data-post');
        $.ajax({
            url: '${pageContext.request.contextPath}/article/addcomment',
            type: 'get',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data: (
                {
                    parrentlevel: level,
                    parrentid: pid,
                    text: commenttext,
                    article: articleid
            }),

            success: function (data) {
                var text=data.content;
                var user=data.author;
                var id=data.id;
                var parentid=data.parent;
                var level=data.level;
                if (level>1){
                    var parrent='#cont'+parentid;
                    var parrentelem=$(parrent);

                    if(!($("#UPComment"+parentid).find('ul.UPComment-container').length!==0)) {
                        if (!($("#UPComment"+parentid).hasClass('Root'))){
                            $("#UPComment"+parentid).addClass('Root').removeClass('ExpandLeaf').removeClass('isLast');
                            var commentlevel=level-1;
                            if (commentlevel<3){
                                $("#UPComment"+parentid).addClass('ExpandOpen');
                            }
                            else $("#UPComment"+parentid).addClass('ExpandClosed');
                        }

                        $("#UPComment"+parentid).append($("<ul>",
                            {
                                "class": "UPComment-container",
                                id: "cont"+parentid
                            }))
                    }

                    $("#cont"+parentid).append($("<li>", {
                        id: "UPComment"+id,
                        'class': 'Node ExpandLeaf isLast'
                    }))
                    $("#UPComment"+id).append($("<div>", {
                        "class":"Expand"
                    })).append($("<div>", {"class": "comment_content",  id:"commcont"+id, 'data-level': level}));

                    $("<div>", {"class":"row"}).appendTo($("#commcont"+id)).append($("<div>", {"class":"col-lg-12", id:"col12"+id}).
                    append([
                            $("<div>", {"class":"row"}).append([
                                $("<div>", {
                                    "class": "col-lg-4",
                                    text: user
                                }),
                                $("<div>", {
                                    "class": "col-lg-4"

                                }),
                                $("<div>", {
                                    "class": "col-lg-4",
                                    text: id
                                })

                            ])
                            ,
                            $("<div>", {"class":"row"}).append(
                                $("<div>", {
                                    "class": "col-lg-12",
                                    text: text
                                }))
                            ,
                            $("<div>", {"class":"row"}).append([
                                $("<div>", {"class":"col-lg-8"}),
                                $("<div>", {"class":"col-lg-4"}).append(
                                    $("<p>", {id: id, text: "Ответить"}).click(showCommentForm).css('cursor', 'pointer')
                                )
                            ])
                        ]
                    ));

                }
                else {
                    $("#UPComments").append($("<li>", {
                        id: "UPComment"+id,
                        'class': "ExpandLeaf Node IsRoot isLast"
                    }));
                    $("#UPComment"+id).append($("<div>", {
                        'class':'Expand'
                    })).append($("<div>", {"class": "comment_content",  id:"commcont"+id, 'data-level': level}));
                    $("<div>", {"class":"row"}).appendTo($("#commcont"+id)).append($("<div>", {"class":"col-lg-12", id:"col12"+id}).
                    append([
                            $("<div>", {"class":"row"}).append([
                                $("<div>", {
                                    "class": "col-lg-4",
                                    text: user
                                }),
                                $("<div>", {
                                    "class": "col-lg-4"

                                }),
                                $("<div>", {
                                    "class": "col-lg-4",
                                    text: id
                                })

                            ])
                            ,
                            $("<div>", {"class":"row"}).append(
                                $("<div>", {
                                    "class": "col-lg-12",
                                    text: text
                                }))
                            ,
                            $("<div>", {"class":"row"}).append([
                                $("<div>", {"class":"col-lg-8"}),
                                $("<div>", {"class":"col-lg-4"}).append(
                                    $("<p>", {id: id, text: "Ответить"}).click(showCommentForm).css('cursor', 'pointer')
                                )
                            ])
                        ]
                    ));
                }


                $('#'+textid).hide();
                $('#'+commentbutid).hide();


            }

        })
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
