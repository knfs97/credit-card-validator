package com.assigment.creditcardvalidator.repository;

import com.assigment.creditcardvalidator.entity.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CardValidatorRepository extends CrudRepository<CreditCard, String> {}
