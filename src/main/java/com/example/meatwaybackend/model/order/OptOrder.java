package com.example.meatwaybackend.model.order;

import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.ad.Advertisement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User buyerUser;

    private Integer quantity;

    private Date killDate;

    private Boolean isConfirmed;

    private Boolean isActive;
}
