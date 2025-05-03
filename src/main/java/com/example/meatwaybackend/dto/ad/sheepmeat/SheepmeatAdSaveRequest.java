package com.example.meatwaybackend.dto.ad.sheepmeat;

import com.example.meatwaybackend.dto.SaveRequestFileDto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record SheepmeatAdSaveRequest(
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
        List<SaveRequestFileDto> files
) {
}
