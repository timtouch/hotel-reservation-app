package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReservationDao;
import com.revature.model.Reservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/api/reservations")
public class ReservationServlet extends HttpServlet
{
    // GET /reservations?{hotelId}
    // GET /reservations?{hotelId}&{guestId}
    // GET /reservations?{guestId}
    // GET /reservations?{guestId}&{hotelRoomId}&{startDate}&{endDate}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);
        ReservationDao reservationDao = new ReservationDao();

        List<Reservation> reservations;
        Reservation reservation;

        resp.setContentType("application/json");
        if(req.getParameterMap().containsKey("hotelId")){
//            if(req.getParameterMap().containsKey("pending")){
//                // Get pending reservations for a hotel
//
//            } else
            if (req.getParameterMap().containsKey("guestId")){
                // Get a guest's reservations for a hotel
                reservations = reservationDao.getAllOfAGuestsReservationForAHotel(Integer.parseInt(req.getParameter("guestId")), Integer.parseInt(req.getParameter("hotelId")));
                mapper.writeValue(resp.getOutputStream(), reservations);
            } else {
                // Get all reservations for a hotel
                reservations = reservationDao.getAllReservationsForHotel(Integer.parseInt(req.getParameter("hotelId")));
                mapper.writeValue(resp.getOutputStream(), reservations);
            }
        } else if (req.getParameterMap().containsKey("guestId")){
            if(req.getParameterMap().containsKey("hotelRoomId") && req.getParameterMap().containsKey("startDate")&&req.getParameterMap().containsKey("endDate")){
                // Get a specific reservation for a guest
                reservation = reservationDao.getAReservation(Integer.parseInt(req.getParameter("guestId")), Integer.parseInt(req.getParameter("hotelRoomId")),
                        Date.valueOf(req.getParameter("startDate")), Date.valueOf(req.getParameter("endDate")));
                mapper.writeValue(resp.getOutputStream(), reservation);
            } else {
                // Get all reservations for a guest
                reservations = reservationDao.getAllOfAGuestsReservation(Integer.parseInt(req.getParameter("guestId")));
                mapper.writeValue(resp.getOutputStream(), reservations);
            }

        }

    }

    // POST /reservations
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doPost(req, resp);
    }

    // PUT /reservations
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doPut(req, resp);
    }

    // DELETE /reservations
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doDelete(req, resp);
    }
}
