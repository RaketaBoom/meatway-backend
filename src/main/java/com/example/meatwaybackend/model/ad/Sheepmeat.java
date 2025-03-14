package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("sheepmeat")
public class Sheepmeat extends Advertisement {
    private Boolean halal;
}
