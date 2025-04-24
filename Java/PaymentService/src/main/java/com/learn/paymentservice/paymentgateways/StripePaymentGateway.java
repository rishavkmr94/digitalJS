package com.learn.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public String createPaymentLink(Long amount, String name, String phoneNumber, String orderId, String email) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(createPriceObject(amount, name).getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder().
                                setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT).
                                setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder().
                                        setUrl("https://google.com").build())
                                .build())
                        .build();
        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getUrl();

    }

    public Price createPriceObject(Long amount,String name) throws StripeException {
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setNickname(name)
                        .setCurrency("usd")
                        .setUnitAmount(amount)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();
        Price price = Price.create(params);
        return price;
    }
}
