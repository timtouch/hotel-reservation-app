package com.revature.model;

import java.sql.Date;

public class Reservation
{
    private int userId;
    private int hoteRoomId;
    private Date startDate;
    private Date endDate;
    private ReservationStatus currentStatus;

    public Reservation()
    {
    }

    public Reservation(int userId, int hoteRoomId, Date startDate, Date endDate, ReservationStatus currentStatus)
    {
        this.userId = userId;
        this.hoteRoomId = hoteRoomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentStatus = currentStatus;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getHoteRoomId()
    {
        return hoteRoomId;
    }

    public void setHoteRoomId(int hoteRoomId)
    {
        this.hoteRoomId = hoteRoomId;
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

    @Override
    public String toString()
    {
        return "Reservation{" +
                "userId=" + userId +
                ", hoteRoomId=" + hoteRoomId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentStatus=" + currentStatus +
                '}';
    }
}
