<%--
  Created by IntelliJ IDEA.
  User: dso75
  Date: 2017-09-21
  Time: 오후 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../style.css"/>">
</head>
<body>
    <h1>Welcome to Spittr</h1>

    <a href="<c:url value="spittles.jsp"/>">Spittles</a>
    <a href="<c:url value="spitter/register.jsp"/>">Register</a>
</body>
</html>
