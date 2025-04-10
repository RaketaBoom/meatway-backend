package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("sheepmeat")
@Getter
@Setter
public class Sheepmeat extends Advertisement {
    private Boolean halal;
}
