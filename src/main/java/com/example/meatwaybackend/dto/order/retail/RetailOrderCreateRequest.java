package com.example.meatwaybackend.dto.order.retail;

public record RetailOrderCreateRequest(
        Long advertisementId,
        Long buyerUserId,
        Double weight
){
}