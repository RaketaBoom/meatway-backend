package com.example.meatwaybackend.model.order;

import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.ad.Advertisement;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RetailOrder {
    @Id
    private Long id;

    private Double weight;

    private Boolean isConfirmed;

    private Boolean isActive;
}
