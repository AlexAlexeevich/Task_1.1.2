<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 08.05.2020
  Time: 5:55
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update user</h1>
<form method="post" action="/updateUser">
    <table cellspacing="0" border="1">
        <tr>
            <td>User Name:</td>
            <td><input type="text" id="name" name="name" value="<c:out value="${defName}" />"></td>
        </tr>
        <%--       <tr>
                   <td>New Name:</td>
                   <td><input type="text" id="newName" name="newName"></td>
               </tr>--%>
        <tr>
            <td>User Password:</td>
            <td><input type="password" id="password" name="password"></td>
        </tr>

    </table>
    <input type="hidden" name="oldName" value="${defName}">
    <br>
    <c:out value="${repeatInput}"/>
    <br>
    <br>
    <input type="submit" value="Update"/>
</form>

<a href="/showUsers">Show Users</a>
</body>
</html>
