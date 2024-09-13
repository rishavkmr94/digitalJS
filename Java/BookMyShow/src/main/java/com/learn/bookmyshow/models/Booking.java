package com.learn.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User user;
    @OneToMany
    @JoinColumn(name = "booking_id")
    private List<ShowSeat> showSeats;
    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;
    private Double amount;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus bookingStatus;
}
