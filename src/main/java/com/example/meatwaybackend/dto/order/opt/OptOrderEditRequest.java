package com.example.meatwaybackend.dto.order.opt;

import java.util.Date;

public record OptOrderEditRequest(
        Integer quantity,
        Date killDate,
        Boolean isConfirmed,
        Boolean isActive
) {
}
