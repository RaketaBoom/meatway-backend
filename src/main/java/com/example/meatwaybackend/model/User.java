package com.example.meatwaybackend.model;

import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.order.OptOrder;
import com.example.meatwaybackend.model.order.RetailOrder;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String city;
    private String phoneNumber;
    private String email;
    @OneToMany
    @JoinColumn(name = "seller_user_id")
    private List<Advertisement> advertisements;
    @OneToMany
    @JoinColumn(name = "buyer_user_id")
    private List<RetailOrder> retailOrders;
    @OneToMany
    @JoinColumn(name = "buyer_user_id")
    private List<OptOrder> optOrders;
}
