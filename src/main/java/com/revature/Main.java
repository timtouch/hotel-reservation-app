package com.revature;

import com.revature.dao.UserDao;

public class Main
{
    public static void main(String[] args)
    {
        UserDao userDao = new UserDao();

        System.out.print(userDao.getAllGuests());
    }
}
