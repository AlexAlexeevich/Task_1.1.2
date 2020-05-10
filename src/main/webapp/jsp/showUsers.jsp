<%@ page import="java.util.ArrayList" %>
<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 06.05.2020
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="/showUsers">

    <table cellspacing="1" border="1" cellpadding="7">
        <tr>
            <th><h3>Id</h3></th>
            <th><h3>Name</h3></th>
            <th><h3>Password</h3></th>
            <th colspan="2"><h3>Action with user</h3></th>
        </tr>
        <c:forEach items="${usersFromServer}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>

                <td><a href="/deleteUser?defaultName=<c:out value='${user.name}'/>">Delete</a></td>
                <td><a href="/updateUser?defaultName=<c:out value='${user.name}'/>">Update</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <table cellspacing="10">
        <tr>
            <td><a href="/">Add Users</a></td>
        </tr>
    </table>
</form>


</body>
</html>
