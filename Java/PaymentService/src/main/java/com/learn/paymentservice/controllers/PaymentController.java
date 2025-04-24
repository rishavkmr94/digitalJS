package com.learn.paymentservice.controllers;

import com.learn.paymentservice.dtos.InitPaymentRequestDto;
import com.learn.paymentservice.paymentgateways.PaymentGateWaySelector;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//objective is to return payment link based on inputs like orderid, amount, name, phone, email

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentGateWaySelector paymentGateWaySelector;

    @PostMapping("/initiate")
    public String initiatePayment(@RequestBody InitPaymentRequestDto initPaymentRequestDto) throws RazorpayException {
        return paymentGateWaySelector.createStandardPaymentLink(
                initPaymentRequestDto.getAmount(),
                initPaymentRequestDto.getName(),
                initPaymentRequestDto.getPhoneNumber(),
                initPaymentRequestDto.getOrderId(),
                initPaymentRequestDto.getEmail()
        );
    }
}
