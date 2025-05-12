package com.example.meatwaybackend.dto.ad;

import com.example.meatwaybackend.dto.FileDto;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
        Boolean isFrozen,
        Boolean isRetail,
        Date dateBegin,
        Date dateEnd,
        Date killDate,
        Boolean isHalal,
        Boolean isMramor,
        Boolean isPremium,
        Boolean hasMedicalCertificate,
        String birdType,
        List<FileDto> files,
        UserProfileResponse sellerUser
) {
}