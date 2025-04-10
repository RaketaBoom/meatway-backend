package com.example.meatwaybackend.model.order;

import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.ad.Advertisement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User buyerUser;

    @NotNull
    private Double weight;

    private Boolean isConfirmed;

    private Boolean isActive;
}
