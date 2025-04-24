package com.learn.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateWaySelector {

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    @Autowired
    private RazorPayPaymentGateway razorPayPaymentGateway;

    public String createStandardPaymentLink(Long amount,String name,String phoneNumber,String orderId,String email) throws RazorpayException, StripeException {
        return stripePaymentGateway.createPaymentLink(amount, name, phoneNumber, orderId, email);
    }

}
