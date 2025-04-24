package com.learn.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface IPaymentGateway {
    public String createPaymentLink(Long amount,String name,String phoneNumber,String orderId,String email) throws RazorpayException, StripeException;
}
