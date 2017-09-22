<%--
  Created by IntelliJ IDEA.
  User: dso75
  Date: 2017-09-21
  Time: 오후 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../style.css"/>">
</head>
<body>
    <h1>Register</h1>

    <sf:form method="POST" commandName="spitter">
        <sf:errors path="*" element="div" cssClass="error"/>
        <sf:label path="firstName" cssErrorClass="error">First Name</sf:label> : <sf:input path="firstName"/> <br>
        <%--<sf:errors path="firstName" cssClass="error"/>--%>
        <sf:label path="lastName" cssErrorClass="error">Last Name</sf:label> : <sf:input path="lastName"/><br>
        <sf:label path="username" cssErrorClass="error">Username</sf:label> : <sf:input path="username"/><br>
        <sf:label path="password" cssErrorClass="error">Password</sf:label> : <sf:password path="password"/><br>

        <input type="submit" value="Register"/>
    </sf:form>
</body>
</html>
