package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.HotelRoomDao;
import com.revature.model.HotelRoom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/hotelRooms")
public class HotelRoomServlet extends HttpServlet
{

    // GET /hotelRooms?{hotelId}
    // GET /hotelRooms?{roomNumber}&{hotelId}
    // GET /hotelRooms?{hotelRoomId}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HotelRoomDao hotelRoomDao = new HotelRoomDao();

        List<HotelRoom> hotelRooms;
        HotelRoom hotelRoom;

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");

        if (req.getParameterMap().containsKey("hotelId"))
        {
            if (req.getParameterMap().containsKey("roomNumber"))
            {
                hotelRoom = hotelRoomDao.getARoomFromAHotelByRoomNumber(Integer.parseInt(req.getParameter("roomNumber")), Integer.parseInt(req.getParameter("hotelId")));
                mapper.writeValue(resp.getOutputStream(), hotelRoom);
            } else
            {
                hotelRooms = hotelRoomDao.getAllRoomsFromAHotel(Integer.parseInt(req.getParameter("hotelId")));
                mapper.writeValue(resp.getOutputStream(), hotelRooms);
            }
        } else if (req.getParameterMap().containsKey("hotelRoomId"))
        {
            hotelRoom = hotelRoomDao.getAHotelRoomByHotelRoomId(Integer.parseInt(req.getParameter("hotelRoomId")));
            mapper.writeValue(resp.getOutputStream(), hotelRoom);
        } else
        {
            resp.setContentType("text/plain");
            resp.getWriter().println("The endpoint needs either a hotelId, both a roomNumber and hotelId, or a hotelRoomId query parameter");
        }

    }

    // POST /hotelRooms
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        HotelRoomDao hotelRoomDao = new HotelRoomDao();

        HotelRoom insertRoom;

        insertRoom = mapper.readValue(req.getInputStream(), HotelRoom.class);

        if (!hotelRoomDao.insertHotelRoom(insertRoom))
        {
            resp.setContentType("text/plain");
            resp.getWriter().println("Could not insert the hotel. Needs a room number, hotel id, and number of beds.");
        }
    }

    // PUT /hotelRooms
    // TODO: Allow user to upload image for a hotel room
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        HotelRoomDao hotelRoomDao = new HotelRoomDao();

        HotelRoom updateRoom;

        updateRoom = mapper.readValue(req.getInputStream(), HotelRoom.class);

        if (!hotelRoomDao.updateHotelRoom(updateRoom))
        {
            resp.setContentType("text/plain");
            resp.getWriter().println("Could not update the hotel. Needs a room number, hotel id,  image url, and number of beds.");
        }
    }
}
