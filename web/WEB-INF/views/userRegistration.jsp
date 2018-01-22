<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 22.01.2018
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<sf:form method="post" modelAttribute="user" action="/user/registration">
    e-mail<sf:input path="email"/><br/>
    login<sf:input path="login"/><br/>
    password<sf:input path="password"/><br/>
    verifypass<sf:input path="matchingPassword"/><br/>
    <input type="submit"/>



</sf:form>

</body>
</html>
