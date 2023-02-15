package com.assigment.creditcardvalidator.service;

import com.assigment.creditcardvalidator.entity.CreditCard;
import com.assigment.creditcardvalidator.exception.*;
import com.assigment.creditcardvalidator.handler.ErrorResponse;
import com.assigment.creditcardvalidator.handler.Response;
import com.assigment.creditcardvalidator.handler.SuccessResponse;
import com.assigment.creditcardvalidator.repository.CardValidatorRepository;
import com.assigment.creditcardvalidator.util.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CardValidatorService {

    private final CardValidatorRepository repository;

    @Autowired
    public CardValidatorService(CardValidatorRepository repository) {
        this.repository = repository;
    }

    public Response validateCard(CreditCard creditCard) {

        String cardNumber = creditCard.getNumber();
        String expirationDate = creditCard.getExpirationDate();

        if(!checkPostParameters(cardNumber, expirationDate)) return ErrorResponse.send("badPostRequest");

        // purge entered data
        cardNumber = cardNumber.trim();
        expirationDate = expirationDate.trim();

        // invalid card format
        if (!checkFormat(cardNumber)) return ErrorResponse.send("malformedCard");
        
        // remove spaces
        cardNumber = removeSpaces(cardNumber);
        
        // not accepted card
        if (!checkType(cardNumber)) return ErrorResponse.send("notAccepted");
        
        // is in the black list
        if (isBlacklisted(cardNumber)) return ErrorResponse.send("blacklisted");
        
        // is invalid after using Luhn formula
        if (isInvalidCardNumber(cardNumber)) return ErrorResponse.send("invalidCardNumber");
        
        // is invalid date format
        if (isInvalidDateFormat(expirationDate)) return ErrorResponse.send("invalidDateFormat");
        
        // expiration date is before current date
        if (isInThePast(expirationDate)) return ErrorResponse.send("invalidDate");

        return SuccessResponse.send("Credit Card is valid");
    }

    public String removeSpaces(String str) {
        return str.replaceAll(" ", "");
    }
    public boolean checkPostParameters(String cardNumber, String expirationDate) {
        return cardNumber != null && expirationDate != null;
    }
    public boolean checkFormat(String cardNumber) {
        return cardNumber.matches(Pattern.ONLY_NUMBERS) || cardNumber.matches(Pattern.EACH_FOUR_DIGIT_A_SPACE);
    }
    public boolean checkType(String cardNumber) {
        return cardNumber.matches(Pattern.VISA_FORMAT) || cardNumber.matches(Pattern.MASTERCARD_FORMAT);
    }
    public boolean isBlacklisted(String cardNumber) {
        Optional<CreditCard> cardOptional = repository.findById(cardNumber);
        return cardOptional.isPresent();
    }
    public boolean isInvalidCardNumber(String cardNumber) {
        int sum = 0;
        int length = cardNumber.length();
        boolean isEven = (length % 2 == 0);

        for (Character c : cardNumber.toCharArray()) {
            int digit = Character.getNumericValue(c);

            if (isEven) {
                digit = digit * 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            isEven = !isEven;
        }
        return sum % 10 != 0;
    }
    public boolean isInvalidDateFormat(String expirationDate) {
        return !expirationDate.matches(Pattern.VALID_DATE_FORMAT);
    }
    public boolean isInThePast(String expirationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate date = LocalDate.parse("01/" + expirationDate, formatter);
        return date.isBefore(LocalDate.now());
    }
}
