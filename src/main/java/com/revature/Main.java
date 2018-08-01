package com.revature;

import com.revature.dao.*;
import com.revature.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        // All the daos used
        UserDao userDao = new UserDao();
        IssueDao issueDao = new IssueDao();
        HotelDao hotelDao = new HotelDao();
        HotelRoomDao hotelRoomDao = new HotelRoomDao();

        System.out.println(issueDao.getAllIssues());

    }
}
