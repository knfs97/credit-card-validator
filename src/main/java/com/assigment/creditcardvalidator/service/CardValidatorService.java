package com.assigment.creditcardvalidator.service;

import com.assigment.creditcardvalidator.entity.CreditCard;
import com.assigment.creditcardvalidator.exception.*;
import com.assigment.creditcardvalidator.repository.CardValidatorRepository;
import com.assigment.creditcardvalidator.util.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void validateCard(CreditCard creditCard) {

        String cardNumber = creditCard.getNumber();
        String expirationDate = creditCard.getExpirationDate();

        // missing parameters in post request
        if (cardNumber == null || expirationDate == null) throw new BadPostRequestException();

        // purge entered data
        String purgedCard = purgeCardNumber(cardNumber);
        String purgedExpirationDate = purgeCardExpirationDate(expirationDate);

        // checking format
        if (!checkFormat(purgedCard)) throw new MalformedCardException();

        // only digits
        purgedCard = purgedCard.replaceAll(" ", "");

        // checking type
        if(!checkCardType(purgedCard)) throw new NotAcceptedCardException();

        // cannot be blacklisted
        if (isBlacklisted(purgedCard)) throw new BlacklistedCardException();

        // validate against Luhn formula
        if (!validateCardNumberAgainstLuhnFormula(purgedCard)) throw new InvalidCardNumberException();

        // validate date format
        if (!isValidDateFormat(purgedExpirationDate)) throw new InvalidDateFormatException();

        // date must be in the future
        if (!isDateInFuture(purgedExpirationDate)) throw new InvalidDateException();

    }

    public String purgeCardNumber(String cardNumber) {
        return cardNumber.trim();
    }

    public String purgeCardExpirationDate(String expirationDate) {return expirationDate.trim();}
    public boolean checkFormat(String cardNumber) {
        return cardNumber.matches(Pattern.ONLY_NUMBERS) ||
               cardNumber.matches(Pattern.EACH_FOUR_DIGIT_A_SPACE);
    }
    public boolean checkCardType(String cardNumber) {
        return cardNumber.matches(Pattern.VISA_FORMAT) || cardNumber.matches(Pattern.MASTERCARD_FORMAT);
    }
    public boolean isBlacklisted(String cardNumber) {
        Optional<CreditCard> cardOptional = repository.findById(cardNumber);
        return cardOptional.isPresent();
    }
    public boolean validateCardNumberAgainstLuhnFormula(String cardNumber) {
        int sum = 0;
        int length = cardNumber.length();
        boolean isEven = (length % 2 == 0);

        for (Character c: cardNumber.toCharArray()) {
            int digit = Character.getNumericValue(c);

            if (isEven) {
                digit = digit * 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            isEven = !isEven;
        }
        return (sum % 10 == 0);
    }
    public boolean isValidDateFormat(String expirationDate) {
        return expirationDate.matches(Pattern.VALID_DATE_FORMAT);
    }
    public boolean isDateInFuture(String expirationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate date = LocalDate.parse("01/" + expirationDate, formatter);
        return date.isAfter(LocalDate.now());
    }
}
