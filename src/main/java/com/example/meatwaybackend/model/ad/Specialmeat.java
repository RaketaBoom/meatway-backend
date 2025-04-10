package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("specialmeat")
@Getter
@Setter
public class Specialmeat extends Advertisement {
    private String animalType;

    private Boolean halal;
}
