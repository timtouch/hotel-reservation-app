package com.revature.dao;

public class ReservationDao
{
    private static String getAllReservationsForHotelQuery = "SELECT * FROM RESERVATION INNER JOIN HOTEL_ROOM ON RESERVATION.HOTEL_ROOM_ID = HOTEL_ROOM.HOTEL_ROOM_ID INNER JOIN HOTEL H on HOTEL_ROOM.HOTEL_ID = H.HOTEL_ID WHERE H.HOTEL_ID = ? ";
    private static String getAllPendingReservationsForAHotelQuery = "SELECT * FROM RESERVATION INNER JOIN HOTEL_ROOM ON RESERVATION.HOTEL_ROOM_ID = HOTEL_ROOM.HOTEL_ROOM_ID INNER JOIN HOTEL H on HOTEL_ROOM.HOTEL_ID = H.HOTEL_ID WHERE H.HOTEL_ID = ? AND CURRENT_STATUS = 'PENDING'";
    private static String getAllOfAGuestsReservationsForAHotelQuery = "SELECT * FROM RESERVATION WHERE USER_ID = 1;";
    private static String getAResevationQuery = "SELECT * FROM RESERVATION WHERE USER_ID = ? AND HOTEL_ROOM_ID = ? AND START_DATE = ? AND END_DATE = ?";
    private static String insertReservation = "{CALL INSERT_RESERVATION}";
    private static String deleteReservation = "{CALL DELETE_RESERVATION}";
}
