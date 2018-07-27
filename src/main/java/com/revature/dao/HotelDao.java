package com.revature.dao;

import com.revature.model.Hotel;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao
{
    private static String getAllHotelsQuery = "SELECT * FROM HOTEL";
    private static String getHotelByIdQuery = "SELECT * FROM HOTEL WHERE HOTEL_ID = ?";
    private static String insertHotelCallable = "{CALL INSERT_HOTEL(?)}"; // (Hotel's Name)

    public List<Hotel> getAllHotels(){
        Hotel hotel;
        List<Hotel> hotelList = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getAllHotelsQuery))
        {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("HOTEL_ID");
                String name = rs.getString("HOTEL_NAME");

                hotel = new Hotel(id, name);

                hotelList.add(hotel);
            }

            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return hotelList;
    }

    public Hotel getHotelById(int id) {
        Hotel hotel = null;

        try (Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getHotelByIdQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int hotelId = rs.getInt("HOTEL_ID");
                String name = rs.getString("HOTEL_NAME");

                hotel = new Hotel(hotelId, name);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return hotel;
    }

    public boolean insertHotel(Hotel hotel){
        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(insertHotelCallable))
        {
            cs.setString(1, hotel.getName());
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
}
