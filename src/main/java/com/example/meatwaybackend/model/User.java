package com.example.meatwaybackend.model;

import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.order.OptOrder;
import com.example.meatwaybackend.model.order.RetailOrder;
import jakarta.persistence.*;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
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
}
