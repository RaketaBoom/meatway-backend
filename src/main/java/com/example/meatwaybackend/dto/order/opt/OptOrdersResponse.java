package com.example.meatwaybackend.dto.order.opt;

import java.util.List;

public record OptOrdersResponse(
        List<OptOrderResponse> orders,
        long size
) {
}
