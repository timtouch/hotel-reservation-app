package com.revature.dao;

import com.revature.model.Reservation;
import com.revature.model.ReservationStatus;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: The other queries
public class ReservationDao
{
    private static String getAllReservationsForHotelQuery = "SELECT * FROM RESERVATION INNER JOIN HOTEL_ROOM ON RESERVATION.HOTEL_ROOM_ID = HOTEL_ROOM.HOTEL_ROOM_ID INNER JOIN HOTEL H on HOTEL_ROOM.HOTEL_ID = H.HOTEL_ID WHERE H.HOTEL_ID = ? ";
    private static String getAllPendingReservationsForAHotelQuery = "SELECT * FROM RESERVATION INNER JOIN HOTEL_ROOM ON RESERVATION.HOTEL_ROOM_ID = HOTEL_ROOM.HOTEL_ROOM_ID INNER JOIN HOTEL H on HOTEL_ROOM.HOTEL_ID = H.HOTEL_ID WHERE H.HOTEL_ID = ? AND CURRENT_STATUS = 'PENDING'";
    private static String getAllOfAGuestsReservationsQuery = "SELECT * FROM RESERVATION WHERE USER_ID = ?";
    private static String getAllOfAGuestsReservationsForAHotelQuery = "SELECT * FROM RESERVATION INNER JOIN HOTEL_ROOM ROOM on RESERVATION.HOTEL_ROOM_ID = ROOM.HOTEL_ROOM_ID INNER JOIN HOTEL H on ROOM.HOTEL_ID = H.HOTEL_ID WHERE USER_ID = ?  AND H.HOTEL_ID = ?";
    private static String getAReservationQuery = "SELECT * FROM RESERVATION WHERE USER_ID = ? AND HOTEL_ROOM_ID = ? AND START_DATE = ? AND END_DATE = ?";
    private static String insertReservation = "{CALL INSERT_RESERVATION(?,?,?,?,?)}";// (user_id, hotel_room_id, start_date, end_date, num_of_guests)
    private static String updateReservationStatus = "UPDATE RESERVATION SET CURRENT_STATUS = ? WHERE USER_ID = ? AND HOTEL_ROOM_ID = ? AND START_DATE = ? AND END_DATE = ?";
    private static String deleteReservation = "{CALL DELETE_RESERVATION(?,?,?,?)}"; // (user_id, hotel_room_id, start_date, end_date)

    @SuppressWarnings("Duplicates")
    public List<Reservation> getAllReservationsForHotel(int hotelId) {
        Reservation reservation = null;
        List<Reservation> reservations = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllReservationsForHotelQuery))
        {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            reservation = mapReservationTableToObject(rs);
            reservations.add(reservation);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return reservations;
    }

    @SuppressWarnings("Duplicates")
    public List<Reservation> getAllPendingReservationsForAHotel(int hotelId) {
        Reservation reservation = null;
        List<Reservation> reservations = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllPendingReservationsForAHotelQuery))
        {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reservation = mapReservationTableToObject(rs);
                reservations.add(reservation);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return reservations;
    }

    @SuppressWarnings("Duplicates")
    public List<Reservation> getAllOfAGuestsReservation(int guestId) {
        Reservation reservation = null;
        List<Reservation> reservations = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllOfAGuestsReservationsQuery))
        {
            ps.setInt(1, guestId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reservation = mapReservationTableToObject(rs);
                reservations.add(reservation);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> getAllOfAGuestsReservationForAHotel (int guestId, int hotelId){
        Reservation reservation = null;
        List<Reservation> reservations = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllOfAGuestsReservationsForAHotelQuery))
        {
            ps.setInt(1, guestId);
            ps.setInt(2, hotelId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reservation = mapReservationTableToObject(rs);

                reservations.add(reservation);
            }

            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return reservations;
    }


    @SuppressWarnings("Duplicates")
    public Reservation getAReservation(int guestId, int hotelRoomId, Date startDate, Date endDate) {
        Reservation reservation = null;

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAReservationQuery))
        {
            ps.setInt(1, guestId);
            ps.setInt(2, hotelRoomId);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reservation = mapReservationTableToObject(rs);
            }

            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return reservation;
    }

    @SuppressWarnings("Duplicates")
    public boolean insertReservation(Reservation reservation){
        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(insertReservation))
        {
            cs.setInt(1, reservation.getUserId());
            cs.setInt(2, reservation.getHotelRoomId());
            cs.setDate(3, reservation.getStartDate());
            cs.setDate(4, reservation.getEndDate());
            cs.setInt(5, reservation.getNumOfGuests());

            cs.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Changes the reservations status of the given reservation
     * @param reservation
     * @param resStat
     * @return
     */
    public boolean updateReservationStatus(Reservation reservation, ReservationStatus resStat){
        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(updateReservationStatus))
        {

            ps.setString(1, resStat.toString());

            ps.setInt(2, reservation.getUserId());
            ps.setInt(3, reservation.getHotelRoomId());
            ps.setDate(4, reservation.getStartDate());
            ps.setDate(5, reservation.getEndDate());


            return ps.executeUpdate() == 1;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("Duplicates")
    public boolean deleteReservation(Reservation reservation){
        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(deleteReservation))
        {
            cs.setInt(1, reservation.getUserId());
            cs.setInt(2, reservation.getHotelRoomId());
            cs.setDate(3, reservation.getStartDate());
            cs.setDate(4, reservation.getEndDate());

            cs.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private Reservation mapReservationTableToObject(ResultSet rs){
        Reservation reservation = null;
        try {
            int userId = rs.getInt("USER_ID");
            int hotelRoomId = rs.getInt("HOTEL_ROOM_ID");
            Date startDate = rs.getDate("START_DATE");
            Date endDate = rs.getDate("END_DATE");
            ReservationStatus reservationStatus = ReservationStatus.valueOf(rs.getString("CURRENT_STATUS"));
            int numOfGuests = rs.getInt("NUM_OF_GUESTS");

            reservation = new Reservation(userId, hotelRoomId, startDate, endDate, reservationStatus, numOfGuests);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return reservation;
    }
}
