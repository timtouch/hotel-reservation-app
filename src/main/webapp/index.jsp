<%@ page import="com.revature.dao.UserDao" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: timothy
  Date: 8/2/18
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Date <%=  new Date() %></title>
</head>
<body>
<nav>
    <ul>
        <li>
            <a href="index.jsp">Home</a>
        </li>
        <li>
            <a href="authentication/login.jsp">Login</a>
        </li>

        <li>
            <a href="authentication/register.jsp">Register</a>
        </li>
    </ul>
</nav>
<script type="application/javascript" src="js/main.js"></script>
</body>
</html>
