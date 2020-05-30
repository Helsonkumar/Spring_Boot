package com.helson.spring_boot.learn_spring_boot;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


//*** Any Custom exception must extend the RuntimeException.
public class UserNotFoundException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public UserNotFoundException(){

    }

    public UserNotFoundException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
