<%--
  Created by IntelliJ IDEA.
  User: Дмитрий Гринец
  Date: 21.05.2016
  Time: 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java"
    pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User CRUD for Javarush</title>

    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>

    <h2>List of Users</h2>
    <br/>
    <form:form method="get" action="/">
        <label>Enter name for search:</label>
        <input type="text" name="name"/>
        <input type="submit" value="Search"/>
    </form:form>
    <form:form method="get" action="/" >
        <label>Enter age for search:</label>
        <input type="number" name="age"/>
        <input type="submit" value="Search"/>
    </form:form>
    <form:form method="get" action="/">
        <input type="submit" value="Show All"/>
    </form:form>
    <form:form method="get" action="/new">
        <input type="submit" value="Add new User"/>
    </form:form>
    <label>Page:</label>
    <div class="pagination">
        <ul>
            <c:forEach begin="${startpage}" end="${endpage}" var="p">
                <c:if test="${name != null}">
                    <a href="<c:url value="/" ><c:param name="page" value="${p}"/><c:param name="name" value="${name}"/>${p}</c:url>"> ${p} </a>
                </c:if>
                <c:if test="${age != null}">
                    <a href="<c:url value="/" ><c:param name="page" value="${p}"/><c:param name="age" value="${age}"/>${p}</c:url>"> ${p} </a>
                </c:if>
                <c:if test="${name == null && age == null}">
                    <a href="<c:url value="/" ><c:param name="page" value="${p}"/>${p}</c:url>"> ${p} </a>
                </c:if>
            </c:forEach>
        </ul>
    </div>
<table frame="border" bgcolor="#ffe4b5">
    <tr>
        <td>ID</td><td>NAME</td><td>AGE</td><td>IS ADMIN</td><td>CREATED DATE</td><td></td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td><a href="<c:url value='/edit-${user.id}-user' />">${user.name}</a></td>
            <td>${user.age}</td>
            <td>${user.isAdmin}</td>
            <td><joda:format value="${user.createdDate}" style="MM"/></td>
            <td><form:form method="get" action="/delete-${user.id}-user">
                <input type="submit" value="delete"/>
            </form:form></td>
        </tr>
    </c:forEach>

</table>
    <br>
</body>
</html>
