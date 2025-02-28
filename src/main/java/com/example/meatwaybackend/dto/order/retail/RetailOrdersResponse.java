package com.example.meatwaybackend.dto.order.retail;

import java.util.List;

public record RetailOrdersResponse(
        List<RetailOrderResponse> orders,
        long size
) {
}
