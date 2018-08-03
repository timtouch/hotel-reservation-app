<%@ page import="com.revature.model.User" %>
<%@ page import="com.revature.dao.UserDao" %>
<%@ page import="com.revature.model.Guest" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: timothy
  Date: 8/2/18
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success</title>
</head>
<body>
<% User user = (User)session.getAttribute("user");
    UserDao userDao = new UserDao();
    List<Guest> guestList = userDao.getAllGuests();
%>

    <h1>Login success for  <%= user.getUsername()%> </h1>

</body>
</html>
