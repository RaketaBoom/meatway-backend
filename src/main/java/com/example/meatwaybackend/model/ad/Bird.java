package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("bird")
@Getter
@Setter
public class Bird extends Advertisement {
    private String birdType;

    private String breed;
}
