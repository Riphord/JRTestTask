<%--
  Created by IntelliJ IDEA.
  User: Дмитрий Гринец
  Date: 21.05.2016
  Time: 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration/Update Confirmation Page</title>
</head>
<body>
message : ${success}
<br/>
<br/>
Go back to <a href="<c:url value='/list' />">List of All Users</a>

</body>

</html>

