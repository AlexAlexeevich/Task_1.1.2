<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 22.05.2020
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>

<form method="post" action="/login">
    <table cellspacing="0" border="1">
        <tr>
            <td>User Name:</td>
            <td><input type="text" id="name" name="name"></td>
        </tr>
        <tr>
            <td>User Password:</td>
            <td><input type="password" id="password" name="password"></td>
        </tr>
    </table>

    <br>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
