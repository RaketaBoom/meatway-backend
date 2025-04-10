package com.example.meatwaybackend.dto.order.opt;

import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.ad.Advertisement;
import java.util.Date;

public record OptOrderResponse(
        Long id,
        Integer quantity,
        Date killDate,
        Boolean isConfirmed,
        Boolean isActive,
        Advertisement advertisement,
        User buyerUser
) {
}
