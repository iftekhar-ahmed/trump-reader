<%--
  Created by IntelliJ IDEA.
  User: Iftekhar Ahmed
  Date: 12/28/17
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="home.css">
    <title>TrumpReader</title>
</head>
<body>
<div id="container">
    <div id="articles">
        <ol>
            <c:forEach items="${feeds}" var="feedMessage">
                <li style="height: 20px;"><a href="${feedMessage.link}" target="_blank">${feedMessage.title}</a></li>
            </c:forEach>
        </ol>
    </div>
    <div id="tweets">
        <h5>@${handle}</h5>
        <ol>
            <c:forEach items="${tweets}" var="status">
                <c:set var="urlMeta" scope="page"
                       value="${fn:split(fn:replace(status.text,\"https://\",'|'), '|')[1]}"/>
                <c:choose>
                    <c:when test="${urlMeta != null}">
                        <c:set var="url" value="https://${urlMeta}"/>
                        <li>${status.text.replace(url, "")} - <a href="${url}">${url}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>${status.text}</li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ol>
    </div>
    <div id="quotes">
        <form:form method="post" action="quotes">
            <table>
                <tr>
                    <td><label>What's on your mind?</label></td>
                    <td><input name="text" type="text"/></td>
                    <td>
                        <blockquote>${quote.value}</blockquote>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enter"/></td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>
