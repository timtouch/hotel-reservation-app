<%--
  Created by IntelliJ IDEA.
  User: timothy
  Date: 8/2/18
  Time: 7:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <nav>
        <ul>

        </ul>
    </nav>
    <form action="/login" method="post">
        Username: <input type="text" name="user" required>
        <br>
        Password: <input type="password" name="pwd" required>
        <br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
