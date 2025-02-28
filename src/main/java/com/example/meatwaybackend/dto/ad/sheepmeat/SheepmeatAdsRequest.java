package com.example.meatwaybackend.dto.ad.sheepmeat;

import java.util.Date;

public record SheepmeatAdsRequest(
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
        boolean isHalal
){
};