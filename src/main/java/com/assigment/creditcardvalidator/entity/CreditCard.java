package com.assigment.creditcardvalidator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blacklisted_cards")
public class CreditCard {

    @Id
    @Column(name = "card_number")
    private String number;
    @Transient
    private String expirationDate;

    public CreditCard(String number) { this.number = number;}

    @Override
    public boolean equals(Object card) {
        if (!(card instanceof CreditCard o)) return false;
        return this.number.equals(o.number) && this.expirationDate.equals(o.expirationDate);
    }

    @Override
    public int hashCode() {return Objects.hash(number, expirationDate);}
}

