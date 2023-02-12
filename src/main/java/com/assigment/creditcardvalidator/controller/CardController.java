package com.assigment.creditcardvalidator.controller;

import com.assigment.creditcardvalidator.entity.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assigment.creditcardvalidator.service.CardValidatorService;

@RestController
@RequestMapping(path = "api/v1/")
public class CardController {

    private final CardValidatorService cardValidatorService;

    @Autowired
    public CardController(CardValidatorService cardValidatorService) {
        this.cardValidatorService = cardValidatorService;
    }

    @PostMapping(path = "validator")
    public ResponseEntity<String> validateCard(@RequestBody CreditCard creditCard){
       cardValidatorService.validateCard(creditCard);
       return new ResponseEntity<>("Valid credit card", HttpStatus.OK);
    }
}
