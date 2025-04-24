package com.learn.paymentservice.paymentgateways;
import com.learn.paymentservice.configurations.RazorPayConfig;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RazorPayPaymentGateway implements IPaymentGateway {
    @Autowired
    private RazorpayClient razorpayClient;

    public String createPaymentLink(Long amount,String name,String phoneNumber,String orderId,String email) throws RazorpayException{

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount); // Amount in paise (50000 = 500 INR)
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("accept_partial", false);
        paymentLinkRequest.put("reference_id", orderId);
        paymentLinkRequest.put("description", "Payment for order #1234");

        JSONObject customer = new JSONObject();
        customer.put("name", name);
        customer.put("contact", phoneNumber);
        customer.put("email", email);

        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);
        paymentLinkRequest.put("expire_by", (System.currentTimeMillis()/1000)+(20*60));
        paymentLinkRequest.put("reminder_enable", true);
        paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method", "get");
        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

        // Return the generated link
        return paymentLink.get("short_url");
    }
}
