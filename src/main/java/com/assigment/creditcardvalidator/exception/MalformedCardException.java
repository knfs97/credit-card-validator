package com.assigment.creditcardvalidator.exception;

public class MalformedCardException extends RuntimeException{
    private static final String MESSAGE = "Card must contain 16 digits, optionally separated by one space each 4 digits";

    public MalformedCardException() { super(MESSAGE);}
}
