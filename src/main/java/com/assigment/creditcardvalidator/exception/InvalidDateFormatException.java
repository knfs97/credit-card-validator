package com.assigment.creditcardvalidator.exception;

public class InvalidDateFormatException extends RuntimeException {
    private static final String MESSAGE = "Invalid date format, expected MM/YY.";

    public InvalidDateFormatException() {super(MESSAGE);}
}
