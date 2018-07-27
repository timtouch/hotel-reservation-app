package com.revature.dao;

import com.revature.model.Hotel;
import com.revature.model.HotelRoom;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO:
public class HotelRoomDao
{
    private static String getAllRoomsFromAHotelQuery = "";
    private static String getARoomFromAHotelByRoomNumberQuery = "";
    private static String insertHotelRoom = "";

    public List<HotelRoom> getAllRoomsFromAHotel(int hotelId){
        HotelRoom hotelRoom;
        List<HotelRoom> hotelRooms = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllRoomsFromAHotelQuery))
        {
            ps.setInt(1, hotelId);
            ps.executeQuery();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return hotelRooms;
    }
}
