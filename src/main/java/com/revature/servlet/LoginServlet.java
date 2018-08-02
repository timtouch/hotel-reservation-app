package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.util.SHA512Hash;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/v1/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.getWriter().println("Got the GET");
    }

    // POST/login
    // For logging in
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("POST User Account");
        UserDao userDao = new UserDao();
        ObjectMapper mapper = new ObjectMapper();
        // Get user data from JSON
        User loginUser = mapper.readValue(req.getInputStream(), User.class);
        User user;

        System.out.println(loginUser);

        // Returns a user JSON if correct
        if (!loginUser.getUsername().isEmpty() && !loginUser.getPassword().isEmpty()){
            user = userDao.getUserByUsername(loginUser.getUsername());
            if (user != null){
                if (user.getPassword().equals(loginUser.getPassword())){
                    resp.setContentType("application/json");
                    mapper.writeValue(resp.getOutputStream(), user);
                } else {
                    resp.setContentType("text/plain");
                    resp.getWriter().println("Username or password is incorrent.");
                }
            } else {
                resp.setContentType("text/plain");
                resp.getWriter().println("Username or password is incorrent.");
            }
        } else {
            resp.setContentType("text/plain");
            resp.getWriter().print("Needs both username and password");
        }

    }
}
