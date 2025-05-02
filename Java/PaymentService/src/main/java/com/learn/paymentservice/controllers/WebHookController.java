package com.learn.paymentservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhook")
public class WebHookController {

    @PostMapping("/stripe")
    public void handleStripeWebhook(@RequestBody String event) {
        System.out.println("Received Stripe webhook event: " + event);
    }
}
