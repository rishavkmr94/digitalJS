package com.learn.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitPaymentRequestDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Long amount;
    private String orderId;
}
