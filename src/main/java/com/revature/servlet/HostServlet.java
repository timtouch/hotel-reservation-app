package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/hosts")
public class HostServlet extends HttpServlet
{
    // GET/hosts
    // GET/hosts?id=""&email=""&username=""
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("GET Host");
        UserDao userDao = new UserDao();

        User host = null;

        int id;
        String email;
        String username;

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");

        if (req.getParameterMap().containsKey("id"))
        {
            id = Integer.parseInt(req.getParameter("id"));
            host = userDao.getUserById(id);
        } else if (req.getParameterMap().containsKey("email"))
        {
            email = req.getParameter("email");
            host = userDao.getUserByEmail(email);
        } else if (req.getParameterMap().containsKey("username"))
        {
            username = req.getParameter("username");
            host = userDao.getUserByUsername(username);
        }

        mapper.writeValue(resp.getOutputStream(), host);

    }
}
