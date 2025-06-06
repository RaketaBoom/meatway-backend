package com.example.meatwaybackend.dto.ad.pork;

import com.example.meatwaybackend.dto.FileDto;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record PorkAdResponse(
        Long id,
        String meatType,
        String title,
        String description,
        BigDecimal price,
        String breed,
        Integer monthsAge,
        Integer weight,
        Integer quantity,
        String location,
        Boolean isFrozen,
        Boolean isRetail,
        Date dateBegin,
        Date dateEnd,
        Date killDate,
        Boolean hasMedicalCertificate,
        Boolean isActive,
        Date creationDate,
        List<FileDto> files,
        String fatContent,
        String processingType,
        UserProfileResponse sellerUser
) {
}