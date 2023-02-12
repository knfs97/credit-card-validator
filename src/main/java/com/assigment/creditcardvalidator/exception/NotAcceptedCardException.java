package com.assigment.creditcardvalidator.exception;

public class NotAcceptedCardException extends RuntimeException{
    private static final String MESSAGE = "Card is not accepted. Accepted cards: Visa with prefix: 4 or Mastercard with prefix: 51 through 55";

    public NotAcceptedCardException() {super(MESSAGE);}
}
