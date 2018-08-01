package com.revature.model;

import com.revature.dao.HotelDao;

public class Host extends User
{
    private int managedHotelId;

    public Host()
    {
    }

    public Host(int userId, String firstName, String lastName, String email, String username, String password, int managedHotelId)
    {
        super(userId, firstName, lastName, email, username, password);
        this.managedHotelId = managedHotelId;
    }

    public int getManagedHotelId()
    {
        return managedHotelId;
    }

    public void setManagedHotelId(int managedHotelId)
    {
        this.managedHotelId = managedHotelId;
    }

    @Override
    public String validateNewUser()
    {
        HotelDao hotelDao = new HotelDao();
        String message = super.validateNewUser();
        if (message.isEmpty()){
            if(hotelDao.getHotelById(this.managedHotelId) == null){
                message = "No hotel with that id exists";
            }
        }
        return message;
    }

    @Override
    public String toString()
    {
        return "Host{" +
                "managedHotelId=" + managedHotelId +
                "} " + super.toString();
    }
}
