package com.example.meatwaybackend.dto.ad.bird;

import java.math.BigDecimal;
import java.util.Date;

public record BirdAdResponse(
        Long id,
        String title,
        String description,
        BigDecimal price,
        String breed,
        Integer monthsAge,
        Integer weight,
        Integer quantity,
        String location,
        boolean isFrozen,
        boolean isRetail,
        Date dateBegin,
        Date dateEnd,
        Date killDate,
        boolean isHalal,
        String birdType,
        boolean hasMedicalCertificate,
        boolean isActive,
        Date creationDate
) {
}