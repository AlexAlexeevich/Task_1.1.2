<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 08.05.2020
  Time: 4:57
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Delete user</h1>
<form method="post" action="/deleteUser">
    <table cellspacing="0" border="1">
        <tr>
            <td>User Name:</td>
            <td><input type="text" id="name" name="name" value="<c:out value="${defName}" />"></td>
        </tr>
        <tr>
            <td>User Password:</td>
            <td><input type="password" id="password" name="password"></td>
        </tr>
    </table>
    <br>
    <c:out value="${repeatInput}"/>
    <br>
    <br>
    <input type="submit" value="Delete"/>
</form>

<a href="/showUsers">Show Users</a>

</body>
</html>
