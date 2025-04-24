package com.learn.paymentservice.configurations;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RazorPayConfig {

    @Value("${razorpay.id}")
    private String razorpayId;
    @Value(("${razorpay.secret}"))
    private String razorpaySecret;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        System.out.println(razorpayId+" "+razorpaySecret);
        return new RazorpayClient(razorpayId, razorpaySecret);
    }

}
