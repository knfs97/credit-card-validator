package com.assigment.creditcardvalidator.exception;

public class InvalidCardNumberException extends RuntimeException {
    private static final String MESSAGE = "Invalid card number";

    public InvalidCardNumberException() {super(MESSAGE);}
}
