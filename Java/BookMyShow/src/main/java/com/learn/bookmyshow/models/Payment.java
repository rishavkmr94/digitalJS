package com.learn.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;
    private Double amount;
    @ManyToOne
    private Booking booking;
}
