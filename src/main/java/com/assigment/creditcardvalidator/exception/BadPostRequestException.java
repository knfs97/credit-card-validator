package com.assigment.creditcardvalidator.exception;

public class BadPostRequestException extends RuntimeException {
    private static final String MESSAGE = "Missing elements in post request";

    public BadPostRequestException() {super(MESSAGE);}
}
