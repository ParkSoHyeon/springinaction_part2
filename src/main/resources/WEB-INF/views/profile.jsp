<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dso75
  Date: 2017-09-22
  Time: 오전 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <h1>Your Profile</h1>

    <c:out value="${spitter.username}"/> <br>
    <c:out value="${spitter.firstName}"/><br>
    <c:out value="${spitter.lasttName}"/><br>
</head>
<body>

</body>
</html>
