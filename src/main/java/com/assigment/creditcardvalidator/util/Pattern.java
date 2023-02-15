package com.assigment.creditcardvalidator.util;

public class Pattern {
    public static final String ONLY_NUMBERS = "^[0-9]{16}$";
    public static final String EACH_FOUR_DIGIT_A_SPACE = "^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$";
    public static final String VALID_DATE_FORMAT = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$";
    public static final String MASTERCARD_FORMAT = "^5[1-5][0-9]{14}$";
    public static final String VISA_FORMAT = "^4[0-9]{15}$";

}
