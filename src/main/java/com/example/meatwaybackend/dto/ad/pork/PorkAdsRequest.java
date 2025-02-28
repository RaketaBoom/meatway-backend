package com.example.meatwaybackend.dto.ad.pork;

import java.util.Date;

public record PorkAdsRequest(
        Boolean isRetail,
        Boolean medicalCertificate,
        Boolean isFrozen,
        Double priceFrom,
        Double priceTo,
        Double weightFrom,
        Double weightTo,
        Integer quantityFrom,
        Integer quantityTo,
        Integer monthsAgeFrom,
        Integer monthsAgeTo,
        Date dateBegin,
        Date dateEnd,
        String fatContent,
        String processingType
) {
}