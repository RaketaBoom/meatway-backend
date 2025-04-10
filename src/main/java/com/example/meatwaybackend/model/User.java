package com.example.meatwaybackend.model;

import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.order.OptOrder;
import com.example.meatwaybackend.model.order.RetailOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String city;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "sellerUser")
    private List<Advertisement> advertisements;
    @OneToMany(mappedBy = "buyerUser")
    private List<RetailOrder> retailOrders;
    @OneToMany(mappedBy = "buyerUser")
    private List<OptOrder> optOrders;

    @Size(min = 3)
    @NotBlank
    @JsonIgnore
    private String passwordDigest;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return passwordDigest;
    }

    @Override
    public String getUsername() {
        return email; //TODO уточнить, а надо ли мыло
    }
}
