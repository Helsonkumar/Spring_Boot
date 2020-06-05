package com.helson.spring_boot.learn_spring_boot;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

//This is a common bean which is thrown aways via the ExcetpionHandlers.
public class GeneralException {
      private String message;
      private LocalDateTime date;
      private HttpStatus status;

    public GeneralException(){

    }


    public GeneralException(String message, LocalDateTime date, HttpStatus status) {
        this.message = message;
        this.date = date;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
