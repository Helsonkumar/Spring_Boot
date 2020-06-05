package com.helson.spring_boot.learn_spring_boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;


//ControllerAdvice would sit in between the "Client and Controller"
//We dont need to extend the ResponseEntityExceptionHandler class but extending it would allow us to leverage most of the
//exception patterns
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

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


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GeneralException exc = new GeneralException(ex.getMessage(),LocalDateTime.now(), HttpStatus.BAD_REQUEST);
        logger.info("Handler : Invalid Argument Exception");
        return new ResponseEntity(exc ,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GeneralException exc = new GeneralException(ex.getMessage(),LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED);
        logger.info("Handler : HttppRequest");
        return new ResponseEntity(exc ,HttpStatus.METHOD_NOT_ALLOWED);
    }


}
