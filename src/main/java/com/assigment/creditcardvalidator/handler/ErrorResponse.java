package com.assigment.creditcardvalidator.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private static final Map<String, String> errorMessages = new HashMap<>(Map.of(
            "blacklisted", "Card is in our list of blacklisted, which has been detected as high risk by our fraud prevention team",
            "badPostRequest", "Missing parameters in post request",
            "invalidCardNumber", "Invalid card number",
            "invalidDate", "Expiration date must be in the future",
            "invalidDateFormat", "Invalid date format, expected MM/YY.",
            "malformedCard", "Card must contain 16 digits, optionally separated by one space each 4 digits",
            "notAccepted", "Card is not accepted. Accepted cards: Visa with prefix: 4 or Mastercard with prefix: 51 through 5"
    ));

    public static Response send(String messageType) {
        return new Response(errorMessages.get(messageType));
    }
}
