package com.learn.paymentservice.advices;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(value = RazorpayException.class)
    public ResponseEntity<Object> handleRazorPayException(Exception e, WebRequest request) {
        return new ResponseEntity<>(
                "RazorpayException occurred: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = StripeException.class)
    public ResponseEntity<Object> handleStripeException(Exception e, WebRequest request) {
        return new ResponseEntity<>(
                "StripeException occurred: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
