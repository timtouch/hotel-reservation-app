package com.revature.model;

import com.revature.dao.UserDao;

public class Guest extends User
{
    public Guest()
    {
    }

    public Guest(int userId, String firstName, String lastName, String email, String username, String password)
    {
        super(userId, firstName, lastName, email, username, password);
    }



    @Override
    public String toString()
    {
        return "Guest{} " + super.toString();
    }
}
