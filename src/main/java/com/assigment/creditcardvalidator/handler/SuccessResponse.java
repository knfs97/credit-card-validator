package com.assigment.creditcardvalidator.handler;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class SuccessResponse {
    public static Response send(String messageType) {
        return new Response(messageType);
    }
}
