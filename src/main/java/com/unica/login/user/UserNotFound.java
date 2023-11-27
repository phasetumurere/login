package com.unica.login.user;

public class UserNotFound extends Throwable{
    public UserNotFound(String message) {
        super(message);
    }
}
