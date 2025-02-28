package com.example.meatwaybackend.dto.ad.pork;

import java.math.BigDecimal;
import java.util.Date;

public record PorkAdSaveRequest(
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
        boolean hasMedicalCertificate
) {
}