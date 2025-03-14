package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("pork")
public class Pork extends Advertisement {
    private String fatContent;

    private String processingType;
}
