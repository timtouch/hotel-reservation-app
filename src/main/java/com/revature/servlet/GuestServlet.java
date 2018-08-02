package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.model.Guest;
import com.revature.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/v1/guests")
public class GuestServlet extends HttpServlet
{
    @Override

    // GET/guests
    // GET/guests/
    // GET/guests?id={id}&email={email}&username={username}
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("GET Guest(s)");
        UserDao userDao = new UserDao();

        List<Guest> guests;
        User user;

        int id;
        String email;
        String username;

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");

        // Different parameters filled uses different methods to search for guests
        if (req.getParameterMap().containsKey("id"))
        {
            id = Integer.parseInt(req.getParameter("id"));
            user = userDao.getUserById(id);
            mapper.writeValue(resp.getOutputStream(), user);
        } else if (req.getParameterMap().containsKey("email"))
        {
            email = req.getParameter("email");
            user = userDao.getUserByEmail(email);
            mapper.writeValue(resp.getOutputStream(), user);
        } else if (req.getParameterMap().containsKey("username"))
        {
            username = req.getParameter("username");
            user = userDao.getUserByUsername(username);
            mapper.writeValue(resp.getOutputStream(), user);
        } else
        {
            guests = userDao.getAllGuests();
            // Write guests object to JSON
            mapper.writeValue(resp.getOutputStream(), guests);
        }


    }

    // POST/guests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("Received POST request for guest");
        UserDao userDao = new UserDao();
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON object and convert to
        Guest guest = mapper.readValue(req.getInputStream(), Guest.class);
        System.out.println(guest);

        String message = guest.validateNewUser();
        if (message.isEmpty())
        {
            userDao.insertGuest(guest);
            userDao.insertUserAccount(guest.getUsername(), guest.getPassword(), userDao.getUserByEmail(guest.getEmail()).getUserId());
        } else
        {
            System.out.println(message);
        }

        System.out.println(userDao.getAllGuests());
        // So they expect a JSON object to be in the response
        resp.setContentType("application/json");
    }

}
