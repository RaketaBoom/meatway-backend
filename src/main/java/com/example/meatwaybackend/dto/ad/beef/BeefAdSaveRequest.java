package com.example.meatwaybackend.dto.ad.beef;

import java.math.BigDecimal;
import java.util.Date;

public record BeefAdSaveRequest(
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
        boolean isMramor,
        boolean hasMedicalCertificate
) {
}