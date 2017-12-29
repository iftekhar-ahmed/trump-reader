<%--
  Created by IntelliJ IDEA.
  User: Iftekhar Ahmed
  Date: 12/28/17
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="home.css">
    <title>Articles From CNN</title>
</head>
<body>
<div id="container">
    <div id="articles">
        <ul>
            <c:forEach items="${feeds}" var="feedMessage">
                <li><a href="${feedMessage.link}" target="_blank">${feedMessage.title}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div id="tweets">
        <ul>
            <c:forEach items="${tweets}" var="status">
                <li>@${status.user.screenName} - ${status.text}</li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
