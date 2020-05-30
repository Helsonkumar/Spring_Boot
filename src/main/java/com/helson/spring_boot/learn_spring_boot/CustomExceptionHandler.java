package com.helson.spring_boot.learn_spring_boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


//ControllerAdvice would sit in between the "Client and Controller"
@ControllerAdvice
public class CustomExceptionHandler  {

    Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    //***Always enusre that Ur custome exception has extended "RuntimException".
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
        GeneralException exc = new GeneralException(ex.getMessage() , LocalDateTime.now() , ex.getStatus());
        logger.info("Handler : Not found Error");
        return new ResponseEntity(exc ,HttpStatus.NOT_FOUND);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex){
        GeneralException exc = new GeneralException(ex.getMessage(),LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR);
        logger.info("Handler : Common Exception");
        return new ResponseEntity(exc ,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
