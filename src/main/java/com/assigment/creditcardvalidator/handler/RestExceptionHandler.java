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
    @ExceptionHandler(BadPostRequestException.class)
    protected ResponseEntity<String> handleCustomAppException() {
        return new ResponseEntity<>("Missing elements in post request", HttpStatus.BAD_REQUEST);
    }
}
