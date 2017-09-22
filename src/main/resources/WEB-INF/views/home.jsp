<%--
  Created by IntelliJ IDEA.
  User: dso75
  Date: 2017-09-21
  Time: 오후 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../style.css"/>">
</head>
<body>
    <h1><s:message code="spittr.welcome"/> </h1>

    <%--<a href="<c:url value="spittles.jsp"/>">Spittles</a>--%>
    <%--<a href="<c:url value="spitter/register.jsp"/>">Register</a>--%>
    <s:url value="/spitter/register" var="registerUrl"/>

    <a href="${registerUrl}">Register</a>
    <s:url value="/spittles" var="spittlesUrl" htmlEscape="true">
        <s:param name="max" value="60"/>
        <s:param name="count" value="20"/>
    </s:url>

    <s:url value="/spitter/{username}" var="spitterUrl">
        <s:param name="username" value="jbauer"/>
    </s:url>

    <s:url value="/spittles" var="spittlesJSUrl" javaScriptEscape="true">
        <s:param name="max" value="60"/>
        <s:param name="count" value="20"/>
    </s:url>

    <script>
        var spittlesUrl = "${spittlesJSUrl}";
    </script>

    <s:escapeBody htmlEscape="true">
        <h1>Hello</h1>
    </s:escapeBody>
</body>
</html>
