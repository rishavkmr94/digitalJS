package com.learn.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateWaySelector {

    @Autowired
    private IPaymentGateway iPaymentGateway;

    public String createStandardPaymentLink(Long amount,String name,String phoneNumber,String orderId,String email) throws RazorpayException {
        return iPaymentGateway.createPaymentLink(amount, name, phoneNumber, orderId, email);
    }

}
