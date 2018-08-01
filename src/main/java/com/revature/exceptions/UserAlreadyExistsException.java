package com.revature.exceptions;

public class UserAlreadyExistsException extends Exception
{
    public UserAlreadyExistsException(String messsage)
    {
        super(messsage);
    }
}
