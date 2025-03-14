package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("bird")
public class Bird extends Advertisement {
    private String birdType;

    private Boolean halal;
}
