<%--
  Created by IntelliJ IDEA.
  User: dso75
  Date: 2017-09-21
  Time: 오후 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../style.css"/>">
</head>
<body>
    <h1>Register</h1>

    <form method="POST">
        First Name : <input type="text" name="firstName"/><br>
        Last Name : <input type="text" name="lastName"/><br>
        Username : <input type="text" name="username"/><br>
        Password : <input type="password" name="password"/><br>

        <input type="submit" value="Register"/>
    </form>
</body>
</html>
