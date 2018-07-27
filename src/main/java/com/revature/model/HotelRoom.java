package com.revature.model;

public class HotelRoom
{
    private int hotelRoomId;
    private int roomNumber;
    private int hotelId;
    private String imageURL;

    public HotelRoom()
    {
    }

    public HotelRoom(int hotelRoomId, int roomNumber, int hotelId, String imageURL)
    {
        this.hotelRoomId = hotelRoomId;
        this.roomNumber = roomNumber;
        this.hotelId = hotelId;
        this.imageURL = imageURL;
    }

    public int getHotelRoomId()
    {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId)
    {
        this.hotelRoomId = hotelRoomId;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public int getHotelId()
    {
        return hotelId;
    }

    public void setHotelId(int hotelId)
    {
        this.hotelId = hotelId;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    @Override
    public String toString()
    {
        return "HotelRoom{" +
                "hotelRoomId=" + hotelRoomId +
                ", roomNumber=" + roomNumber +
                ", hotelId=" + hotelId +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
