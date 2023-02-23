package com.chillmo.developer.tournamentApp.user.exceptions;

public class UserAlreadyExistsException  extends RuntimeException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
