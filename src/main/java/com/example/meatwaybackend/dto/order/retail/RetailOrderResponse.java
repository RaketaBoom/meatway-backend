package com.example.meatwaybackend.dto.order.retail;

public record RetailOrderResponse(
        Long advertisementId,
        Long buyerUserId,
        Double weight,
        Boolean isConfirmed,
        Boolean isActive
){
}