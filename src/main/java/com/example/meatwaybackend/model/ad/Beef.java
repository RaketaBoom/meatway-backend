package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("beef")
public class Beef extends Advertisement {
    private Boolean halal;
    private Boolean isMramor;
    private String feedingType;
}
