
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 25.05.2020
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user">

    <h1>Hello <c:out value="${user.name}"/></h1>



    <table cellspacing="0" border="2">
        <tr>
            <td>User Name:</td>
            <td><input type="text" id="name" value="${user.name}"></td>
        </tr>
        <tr>
            <td>User Password:</td>
            <td><input type="text" id="password" value="${user.password}"></td>
        </tr>
        <tr>
            <td>User Role:</td>
            <td><input type="text" id="role" value="${user.role}"></td>
        </tr>
    </table>

    <br>
    <table cellspacing="10">
        <tr>
            <td><a href="${pageContext.request.contextPath}/login">Login</a></td>
        </tr>
    </table>
</form>
</body>
</html>
