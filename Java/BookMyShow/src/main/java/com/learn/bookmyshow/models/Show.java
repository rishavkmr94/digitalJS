package com.learn.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel{
    private String showName;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;
    private Date startTime;
}
