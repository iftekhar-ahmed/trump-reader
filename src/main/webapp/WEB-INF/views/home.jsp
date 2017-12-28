<%--
  Created by IntelliJ IDEA.
  User: BS-118
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
    <%--<style type="text/css">
        body {
            background-image: url('https://goo.gl/dpJ3Sd');
        }
    </style>--%>
    <title>Articles From CNN</title>
</head>
<body>
<ul>
    <c:forEach items="${result}" var="feedMessage">
        <li><a href="${feedMessage.link}">${feedMessage.title}</a></li>
    </c:forEach>
</ul>
</body>
</html>
