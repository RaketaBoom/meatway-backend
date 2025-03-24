package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("specialmeat")
public class Specialmeat extends Advertisement {
    private String animalType;

    private Boolean halal;
}
