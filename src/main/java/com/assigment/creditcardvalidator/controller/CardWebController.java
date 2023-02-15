package com.assigment.creditcardvalidator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardWebController {

    @GetMapping("/")
    public String getValidator() {
        return "validator";
    }
}
