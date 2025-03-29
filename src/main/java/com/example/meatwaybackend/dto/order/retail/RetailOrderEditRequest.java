package com.example.meatwaybackend.dto.order.retail;

public record RetailOrderEditRequest(
        Double weight,
        Boolean isConfirmed,
        Boolean isActive
) {
}
