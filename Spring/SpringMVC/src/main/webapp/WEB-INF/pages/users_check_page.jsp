<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: averveyko
  Date: 29/10/2017
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:form modelAttribute="userFromServer" method="post" action="/springmvc/users/check">
    <spring:input path="login"/>
    <spring:input path="password"/>
    <spring:button>Check user</spring:button>
</spring:form>
</body>
</html>
