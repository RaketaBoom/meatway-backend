package com.example.meatwaybackend.model.ad;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("pork")
@Getter
@Setter
public class Pork extends Advertisement {
    private String fatContent;

    private String processingType;
}
