package com.example.meatwaybackend.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OptOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Date killDate;

    private Boolean isConfirmed;

    @Column(name="is_active")
    private Boolean isActive;
}
