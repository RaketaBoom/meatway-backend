package com.example.meatwaybackend.dto.ad.beef;

import com.example.meatwaybackend.dto.SaveRequestFileDto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record BeefAdSaveRequest(
        String meatType, //убрать
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
        boolean isMramor,
        boolean hasMedicalCertificate,
        List<SaveRequestFileDto> files
) {
}