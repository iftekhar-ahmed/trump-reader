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
    <style>
        <%@ include file="home.css" %>
    </style>
    <title>TrumpReader</title>
</head>
<body>
<h3 style="margin-left:30px;">Get a random quote about Donald Trump</h3>
<div id="container">
    <div id="quotes">
        <form:form method="post">
            <table>
                <tr>
                    <td style="width:15%"><label>What's on your mind?</label></td>
                    <td style="width:15%;text-align: center"><input placeholder="At least 3 characters..." name="text"
                                                                    type="text"/></td>
                    <td style="width:70%">
                        <blockquote style="background: azure">
                            <c:if test="${not empty quote.value}">
                                ${quote.value}
                            </c:if>
                        </blockquote>
                    </td>
                </tr>
                <tr>
                    <td style="width:15%"></td>
                    <td style="width:15%;text-align: center"><input type="submit" value="Enter"/></td>
                    <td style="width:70%;padding-left:40px;">
                        <c:if test="${not empty quote._embedded.source}">
                            <c:set var="sourceUrl" value="${quote._embedded.source.get(0).url}"/>
                            Source: <a href="${sourceUrl}" target="_blank">${sourceUrl}</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
    <div id="articles">
        <h3 style="margin-left:30px;">Articles from CNN</h3>
        <ol class="ols">
            <c:forEach items="${feeds}" var="feedMessage">
                <li><a href="${feedMessage.link}" target="_blank">${feedMessage.title}</a></li>
            </c:forEach>
        </ol>
    </div>
    <div id="tweets">
        <h3>@${handle}</h3>
        <ol class="ols">
            <c:forEach items="${tweets}" var="status">
                <c:set var="urlMeta" scope="page"
                       value="${fn:split(fn:replace(status.text,\"https://\",'|'), '|')[1]}"/>
                <c:choose>
                    <c:when test="${urlMeta != null}">
                        <c:set var="url" value="https://${urlMeta}"/>
                        <li>${status.text.replace(url, "")} - <a href="${url}" target="_blank">${url}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>${status.text}</li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ol>
    </div>

</div>
</body>
</html>
