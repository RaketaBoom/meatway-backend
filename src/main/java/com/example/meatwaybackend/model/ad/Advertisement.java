package com.example.meatwaybackend.model.ad;

import com.example.meatwaybackend.model.File;
import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.order.OptOrder;
import com.example.meatwaybackend.model.order.RetailOrder;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ad_type",
        discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private String breed;

    private Integer monthsAge;

    private Double weight;

    private Integer quantity;

    private String location;

    private Boolean isFrozen;

    private Boolean isRetail;

    private Date killBegin;

    private Date killEnd;

    private Date killDate;

    private Boolean hasMedicalCertificate;

    private Boolean isActive;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User sellerUser;

    @OneToMany(mappedBy = "advertisement")
    private List<RetailOrder> retailOrders;

    @OneToMany(mappedBy = "advertisement")
    private List<OptOrder> optOrders;

    @OneToMany(cascade = CascadeType.ALL)
    private List<File> files;
}
