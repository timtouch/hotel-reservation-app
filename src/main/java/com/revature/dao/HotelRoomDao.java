package com.revature.dao;

import com.revature.model.HotelRoom;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRoomDao
{
    private static String getAllRoomsFromAHotelQuery = "SELECT * FROM HOTEL_ROOM WHERE HOTEL_ID = ?";
    private static String getARoomFromAHotelByRoomNumberQuery = "SELECT * FROM HOTEL_ROOM INNER JOIN HOTEL H on HOTEL_ROOM.HOTEL_ID = H.HOTEL_ID WHERE ROOM_NUMBER = ? AND H.HOTEL_ID = ?";
    private static String getAHotelRoomByHotelRoomIdQuery = "SELECT * FROM HOTEL_ROOM WHERE HOTEL_ROOM_ID = ?";
    private static String insertHotelRoom = "{CALL INSERT_HOTEL_ROOM(?,?,?)}"; // (ROOM_NUMBER, HOTEL_ID, NUM_OF_BEDS)
    private static String updateHotelRoom = "UPDATE HOTEL_ROOM SET ROOM_NUMBER = ?, IMAGE_URL = ?, NUM_OF_BEDS = ? WHERE HOTEL_ROOM_ID = ?";

    public List<HotelRoom> getAllRoomsFromAHotel(int hotelId){
        HotelRoom hotelRoom;
        List<HotelRoom> hotelRooms = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllRoomsFromAHotelQuery))
        {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                hotelRoom = mapHotelRoomTableToObject(rs);
                hotelRooms.add(hotelRoom);
            }

            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return hotelRooms;
    }


    public HotelRoom getARoomFromAHotelByRoomNumber(int roomNumber, int hotelId){
        HotelRoom hotelRoom = null;

        try (Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getARoomFromAHotelByRoomNumberQuery))
        {
            ps.setInt(1, roomNumber);
            ps.setInt(2, hotelId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                hotelRoom = mapHotelRoomTableToObject(rs);
            }

            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return hotelRoom;
    }

    public HotelRoom getAHotelRoomByHotelRoomId(int hotelRoomId){
        HotelRoom hotelRoom = null;

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(getAHotelRoomByHotelRoomIdQuery))
        {
            ps.setInt(1, hotelRoomId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                hotelRoom = mapHotelRoomTableToObject(rs);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return hotelRoom;
    }

    public boolean insertHotelRoom(HotelRoom hotelRoom){
        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(insertHotelRoom))
        {
            cs.setInt(1, hotelRoom.getRoomNumber());
            cs.setInt(2, hotelRoom.getHotelId());
            cs.setInt(3, hotelRoom.getNumOfBeds());

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

    public boolean updateHotelRoom(HotelRoom hotelRoom){
        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(updateHotelRoom))
        {
            cs.setInt(1, hotelRoom.getRoomNumber());
            cs.setString(2, hotelRoom.getImageURL());
            cs.setInt(3, hotelRoom.getNumOfBeds());
            cs.setInt(4, hotelRoom.getHotelRoomId());


            cs.executeUpdate();
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

    public HotelRoom mapHotelRoomTableToObject(ResultSet rs){
        HotelRoom hotelRoom = null;

        try{
            int hotelRoomId = rs.getInt("HOTEL_ROOM_ID");
            int roomNumber = rs.getInt("ROOM_NUMBER");
            int hotelId = rs.getInt("HOTEL_ID");
            String imageUrl = rs.getString("IMAGE_URL");
            int numOfBeds = rs.getInt("NUM_OF_BEDS");

            hotelRoom = new HotelRoom(hotelRoomId, roomNumber, hotelId, imageUrl, numOfBeds);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return hotelRoom;
    }
}
