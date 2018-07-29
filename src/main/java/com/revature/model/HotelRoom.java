package com.revature.model;


public class HotelRoom
{
    private int hotelRoomId;
    private int roomNumber;
    private int hotelId;
    private String imageURL;
    private int numOfBeds;

    public HotelRoom()
    {
    }

    public HotelRoom(int hotelRoomId, int roomNumber, int hotelId, String imageURL, int numOfBeds)
    {
        this.hotelRoomId = hotelRoomId;
        this.roomNumber = roomNumber;
        this.hotelId = hotelId;
        this.imageURL = imageURL;
        this.numOfBeds = numOfBeds;
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

    public int getNumOfBeds()
    {
        return numOfBeds;
    }

    public void setNumOfBeds(int numOfBeds)
    {
        this.numOfBeds = numOfBeds;
    }

    @Override
    public String toString()
    {
        return "HotelRoom{" +
                "hotelRoomId=" + hotelRoomId +
                ", roomNumber=" + roomNumber +
                ", hotelId=" + hotelId +
                ", imageURL='" + imageURL + '\'' +
                ", numOfBeds=" + numOfBeds +
                '}';
    }
}
