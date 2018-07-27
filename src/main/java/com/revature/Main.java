package com.revature;

import com.revature.dao.HotelDao;
import com.revature.dao.UserDao;
import com.revature.model.Guest;
import com.revature.model.Host;
import com.revature.model.Hotel;

public class Main
{
    public static void main(String[] args)
    {
        UserDao userDao = new UserDao();
        HotelDao hotelDao = new HotelDao();

        Hotel hotel = new Hotel();
        hotel.setName("Hilton");

        System.out.println(hotelDao.getHotelById(3));
    }
}
