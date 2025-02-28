package com.example.meatwaybackend.dto.ad;

import java.util.Date;

public record AdsRequest(
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
        Date dateEnd
){
}