package com.example.meatwaybackend.model.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class OptOrder {
    @Id
    private Long id;

    private Integer quantity;

    private Date killDate;

    private Boolean isConfirmed;

    private Boolean isActive;
}
