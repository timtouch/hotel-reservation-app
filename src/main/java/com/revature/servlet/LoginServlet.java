package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.util.SHA512Hash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    UserDao userDao = new UserDao();

    // For logging in
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        User user = userDao.getUserByUsername(username);

        if(user != null && user.getPassword().equals(SHA512Hash.getSHA512SecurePassword(pwd))){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            System.out.println("Login Successful");
            // Expires in 30 mins
            session.setMaxInactiveInterval(30*60);
            resp.sendRedirect("/authentication/loginSuccess.jsp");
        } else {
            RequestDispatcher rd= getServletContext().getRequestDispatcher("/authentication/login.jsp");

            PrintWriter out = resp.getWriter();

            out.println("<font color=red> Either user name or password is wrong. </font>");
            rd.include(req, resp);
        }
    }
}
