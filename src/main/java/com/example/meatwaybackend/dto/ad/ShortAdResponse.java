package com.example.meatwaybackend.dto.ad;

import java.math.BigDecimal;
import java.util.Date;

public record ShortAdResponse(
        Long id,
        String title,
        BigDecimal price,
        String animalType,
        String breed,
        Integer monthsAge,
        Double weight,
        Integer quantity,
        String location,
        String username,
        Double stars,
        boolean isFrozen,
        boolean isRetail,
        Date dateBegin,
        Date dateEnd,
        Date killDate,
        boolean isHalal,
        boolean isMramor,
        boolean isPremium,
        boolean hasMedicalCertificate,
        String birdType
) {
}