package com.learn.bookmyshow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String title;
    private String duration;
    private String releaseDate;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Actor> actors;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Language> languages;
}
