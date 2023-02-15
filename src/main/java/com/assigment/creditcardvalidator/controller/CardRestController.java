package com.assigment.creditcardvalidator.controller;

import com.assigment.creditcardvalidator.entity.CreditCard;
import com.assigment.creditcardvalidator.handler.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assigment.creditcardvalidator.service.CardValidatorService;

@RestController
@RequestMapping(path = "api/v1/")
public class CardRestController {

    private final CardValidatorService cardValidatorService;

    @Autowired
    public CardRestController(CardValidatorService cardValidatorService) {
        this.cardValidatorService = cardValidatorService;
    }

    @PostMapping(path = "/validator")
    public ResponseEntity<Response> validateCard(@RequestBody CreditCard creditCard) {
        Response res = cardValidatorService.validateCard(creditCard);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
