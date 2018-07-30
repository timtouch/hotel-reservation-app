package com.revature.dao;


import com.revature.model.Guest;
import com.revature.model.Host;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao
{
    // Maybe make query to get all guest for a hotel?
//    private static String getAllGuestsThatHasReservedInHotelQuery = "SELECT * FROM HOTEL_USER LEFT OUTER JOIN USER_ACCOUNT ON HOTEL_USER.USER_ID = USER_ACCOUNT.USER_ID WHERE USER_ROLE = 'GUEST'";
//    private static String getAllHostsForAHotelQuery = "SELECT * FROM HOTEL_USER LEFT OUTER JOIN USER_ACCOUNT ON HOTEL_USER.USER_ID = USER_ACCOUNT.USER_ID WHERE USER_ROLE =  'HOST' WHERE HOTEL_ID = ?";
    private static String getAllGuestsQuery = "SELECT * FROM HOTEL_USER LEFT OUTER JOIN USER_ACCOUNT ON HOTEL_USER.USER_ID = USER_ACCOUNT.USER_ID WHERE USER_ROLE = 'GUEST'";
    private static String getHotelUserByIdQuery = "SELECT * FROM HOTEL_USER LEFT OUTER JOIN USER_ACCOUNT ON HOTEL_USER.USER_ID = USER_ACCOUNT.USER_ID WHERE HOTEL_USER.USER_ID = ?";
    private static String getHotelUserByEmailQuery = "SELECT * FROM HOTEL_USER LEFT OUTER JOIN USER_ACCOUNT ON HOTEL_USER.USER_ID = USER_ACCOUNT.USER_ID WHERE EMAIL = ?";
    private static String getHotelUserByUsernameQuery = "SELECT * FROM HOTEL_USER LEFT OUTER JOIN USER_ACCOUNT ON HOTEL_USER.USER_ID = USER_ACCOUNT.USER_ID WHERE USERNAME = ?";

    private static String insertHotelHostCallable = "{CALL INSERT_HOST(?,?,?,?)}"; // (FIRST_NAME, LAST_NAME, EMAIL, MANAGED_HOTEL_ID)
    private static String insertHotelGuestCallable = "{CALL INSERT_GUEST (?,?,?)}"; // (FIRST_NAME, LAST_NAME, EMAIL)
    private static String insertUserAccountCallable = "{CALL INSERT_USER_ACCOUNT (?,?,?)}"; // (USERNAME, USER_PASSWORD, USER_ID)

    public List<Guest> getAllGuests(){
        Guest guest;

        List<Guest> guestList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps= conn.prepareStatement(getAllGuestsQuery))
        {
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                guest = (Guest) mapUserTableToObject(rs);
                guestList.add(guest);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return guestList;
    }

    public User getUserById(int id) {
        User user = null;

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(getHotelUserByIdQuery))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                user = mapUserTableToObject(rs);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @SuppressWarnings("Duplicates")
    public User getUserByEmail(String email){
        User user = null;

        try (Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(getHotelUserByEmailQuery))
        {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = mapUserTableToObject(rs);
            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return user;

    }

    @SuppressWarnings("Duplicates")
    public User getUserByUsername(String usrname) {
        User user = null;

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(getHotelUserByUsernameQuery))
        {
            ps.setString(1,usrname);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                user = mapUserTableToObject(rs);

            }
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    public boolean insertHost(Host host){

        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(insertHotelHostCallable))
        {
            cs.setString(1, host.getFirstName());
            cs.setString(2, host.getLastName());
            cs.setString(3, host.getEmail());
            cs.setInt(4, host.getManagedHotelId());

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
    public boolean insertGuest(Guest guest){


        try(Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(insertHotelGuestCallable))
        {
            cs.setString(1, guest.getFirstName());
            cs.setString(2, guest.getLastName());
            cs.setString(3, guest.getEmail());

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

    public boolean insertUserAccount(String username, String password, int userId){

        try (Connection conn = ConnectionUtil.getConnection();
            CallableStatement cs = conn.prepareCall(insertUserAccountCallable))
        {
            cs.setString(1,username);
            cs.setString(2, password);
            cs.setInt(3, userId);
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
     * Maps the user from a table to an object given the ResultSet
     * @param rs
     * @return
     */
    private User mapUserTableToObject(ResultSet rs){
        User user = null;
        try {
            int userId = rs.getInt("USER_ID");
            String firstName = rs.getString("FIRST_NAME");
            String lastName = rs.getString("LAST_NAME");
            String email = rs.getString("EMAIL");
            String username = rs.getString("USERNAME");
            String password = rs.getString("USER_PASSWORD");

            if (rs.getString("USER_ROLE").equals("GUEST"))
            {
                user = new Guest(userId, firstName, lastName, email, username, password);
            } else {
                int managedHotelId = rs.getInt("HOTEL_MANAGED");
                user = new Host(userId,firstName, lastName, email, username, password, managedHotelId);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }
}
