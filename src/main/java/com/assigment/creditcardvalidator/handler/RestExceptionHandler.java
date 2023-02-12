package com.assigment.creditcardvalidator.handler;

import com.assigment.creditcardvalidator.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BlacklistedCardException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAppException(BlacklistedCardException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCardNumberException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAppException(InvalidCardNumberException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAppException(InvalidDateFormatException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidDateException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAppException(InvalidDateException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MalformedCardException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAppException(MalformedCardException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAcceptedCardException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAppException(NotAcceptedCardException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadPostRequestException.class)
    protected ResponseEntity<ErrorResponse> handleCustomAppException(BadPostRequestException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
