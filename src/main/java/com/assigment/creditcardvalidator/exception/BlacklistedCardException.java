package com.assigment.creditcardvalidator.exception;

public class BlacklistedCardException extends RuntimeException{
    private static final String MESSAGE = "Card is in our list of blacklisted, which has been detected as high risk by our fraud prevention team";

    public BlacklistedCardException() {super(MESSAGE);}
}
