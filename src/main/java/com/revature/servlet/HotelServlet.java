package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.HotelDao;
import com.revature.model.Host;
import com.revature.model.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/hotels")
public class HotelServlet extends HttpServlet
{
    private HotelDao hotelDao = new HotelDao();

    // GET /hotels
    // GET /hotels?{id}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Hotel> hotels;
        Hotel hotel;

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");

        if(req.getParameterMap().containsKey("id")){
            mapper.writeValue(resp.getOutputStream(), hotelDao.getHotelById(Integer.parseInt(req.getParameter("id"))));
        } else {
            mapper.writeValue(resp.getOutputStream(), hotelDao.getAllHotels());
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Hotel insertHotel;

        ObjectMapper mapper = new ObjectMapper();

        insertHotel = mapper.readValue(req.getInputStream(), Hotel.class);

        if (!insertHotel.getName().isEmpty()){
            hotelDao.insertHotel(insertHotel);
        } else {
            resp.getWriter().println("Hotel needs a name");
        }
    }
}
