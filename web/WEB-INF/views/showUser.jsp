<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: VRostov
  Date: 22.01.2018
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

login: <c:out value="${user.login}"/><br/>
email: <c:out value="${user.email}"/><br/>

<sf:form action="/user/submit" method="post" modelAttribute="user">
    <sf:hidden path="login" />
    Заадминить: <sf:checkbox path="makeadmin"/><br/>
    <input type="submit">

</sf:form>

</body>
</html>
