package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("beef")
@Getter
@Setter
public class Beef extends Advertisement {
    private Boolean isHalal;
    private Boolean isMramor;
    private String feedingType;
    private String cuttingType;
}
