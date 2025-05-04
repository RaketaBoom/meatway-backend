package com.example.meatwaybackend.dto.ad.specialmeat;

import com.example.meatwaybackend.dto.FileDto;
import com.example.meatwaybackend.dto.user.UserProfileResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record SpecialmeatAdResponse(
        Long id,
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
        boolean hasMedicalCertificate,
        boolean isActive,
        Date creationDate,
        List<FileDto> files,
        UserProfileResponse sellerUser
) {
}