package com.example.meatwaybackend.dto.ad;

import com.example.meatwaybackend.dto.FileDto;
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
        boolean isFrozen,
        boolean isRetail,
        Date dateBegin,
        Date dateEnd,
        Date killDate,
        boolean isHalal,
        boolean isMramor,
        boolean isPremium,
        boolean hasMedicalCertificate,
        String birdType,
        List<FileDto> files
) {
}