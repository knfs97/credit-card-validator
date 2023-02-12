package com.assigment.creditcardvalidator.exception;

public class InvalidDateException extends RuntimeException{
    private static final String MESSAGE = "Expiration date must be in the future";
    public InvalidDateException() {super(MESSAGE);}
}
