<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 15.01.2018
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/summernote.css">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resources/css/summernote.css" rel="stylesheet">

    <link href="/resources/css/style.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/lang/summernote-ru-RU.js"></script>
    <script src="/resources/js/summernote.js"></script>
    <script src="/resources/js/script.js"></script>
</head>
<body>

<script>
    function confirmForChanges() {
        var r = confirm("Do you want to proceed");
        if (r) {
            alert("Your changes have been saved");
        } else {
            alert("changes not saved")
        }
        return r;//if true then submit else don't submit
    }

</script>



<sf:form method="post" modelAttribute="createdArticle" onsubmit="confirmForChanges()">
name<sf:input path="name"/><br/>
    <sf:textarea path="content" id="text" />
    <input type="submit"/>

</sf:form>
<br/>

${requestScope['javax.servlet.forward.request_uri']}
<br/>



</body>
</html>
