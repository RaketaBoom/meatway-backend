package com.example.meatwaybackend.dto.ad.bird;

import java.util.Date;

public record BirdAdsRequest(
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
        boolean isHalal,
        String birdType
){
}