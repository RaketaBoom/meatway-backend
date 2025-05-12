package com.example.meatwaybackend.dto.ad.specialmeat;

import java.util.Date;

public record SpecialmeatAdsRequest(
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
        Boolean isHalal,
        String animalType
) {
}