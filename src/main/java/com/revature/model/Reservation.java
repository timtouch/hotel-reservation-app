package com.revature.model;


import java.sql.Date;

public class Reservation
{
    private int userId;
    private int hotelRoomId;
    private Date startDate;
    private Date endDate;
    private ReservationStatus currentStatus;
    private int numOfGuests;

    public Reservation()
    {
    }

    public Reservation(int userId, int hotelRoomId, Date startDate, Date endDate, ReservationStatus currentStatus, int numOfGuests)
    {
        this.userId = userId;
        this.hotelRoomId = hotelRoomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentStatus = currentStatus;
        this.numOfGuests = numOfGuests;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getHotelRoomId()
    {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId)
    {
        this.hotelRoomId = hotelRoomId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public ReservationStatus getCurrentStatus()
    {
        return currentStatus;
    }

    public void setCurrentStatus(ReservationStatus currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public int getNumOfGuests()
    {
        return numOfGuests;
    }

    public void setNumOfGuests(int numOfGuests)
    {
        this.numOfGuests = numOfGuests;
    }

    @Override
    public String toString()
    {
        return "Reservation{" +
                "userId=" + userId +
                ", hotelRoomId=" + hotelRoomId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentStatus=" + currentStatus +
                ", numOfGuests=" + numOfGuests +
                '}';
    }
}
