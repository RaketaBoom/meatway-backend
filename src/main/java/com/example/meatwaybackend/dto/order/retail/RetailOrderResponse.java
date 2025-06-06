package com.example.meatwaybackend.dto.order.retail;

import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.ad.Advertisement;

public record RetailOrderResponse(
        Advertisement advertisement,
        User buyerUser,
        Double weight,
        Boolean isConfirmed,
        Boolean isActive
){
}