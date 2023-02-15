package com.assigment.creditcardvalidator.util;

import com.assigment.creditcardvalidator.entity.CreditCard;
import com.assigment.creditcardvalidator.repository.CardValidatorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataConfigInit {

    @Bean
    CommandLineRunner commandLineRunner(CardValidatorRepository cardValidatorRepository) {
        return args -> {
            CreditCard blacklisted1 = new CreditCard("4788384538552446");
            CreditCard blacklisted2 = new CreditCard("5144385438523845");
            cardValidatorRepository.saveAll(List.of(blacklisted1, blacklisted2));
        };
    }
}
