package com.revature.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.revature.dao.UserDao;
import com.revature.util.SHA512Hash;

// Reference: http://www.baeldung.com/jackson-inheritance
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Guest.class, name = "guest"),
        @JsonSubTypes.Type(value = Host.class, name = "host")
})
public abstract class User
{
    private int userId;
    private String firstName;
    private String lastName;
    private String email;

    private String username;
    private String password;

    public User()
    {
    }

    public User(int userId, String firstName, String lastName, String email, String username, String password)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = SHA512Hash.getSHA512SecurePassword(password);
    }

    public String validateNewUser(){
        UserDao userDao = new UserDao();
        String message = "";

         if (getFirstName().isEmpty() || getLastName().isEmpty() || getEmail().isEmpty()
                 || getUsername().isEmpty() || getPassword().isEmpty()){
             message = "You must fill out all the fields";
         } else if (userDao.getUserByUsername(getUsername()) != null) {
             message = "A user with that username already exists";
         } else if (userDao.getUserByEmail(getEmail()) != null)
         {
            message = "A user with that email already exists";
         }
         return message;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
