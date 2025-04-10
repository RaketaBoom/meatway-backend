package com.example.meatwaybackend.dto.order.opt;

import java.util.Date;

public record OptOrderCreateRequest(
        Long advertisementId,
        Long buyerUserId,
        Integer quantity,
        Date killDate
) {
}
