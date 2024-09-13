package com.learn.bookmyshow.Exceptions;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String msg){
        super(msg);
    }
}
