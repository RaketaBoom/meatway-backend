package com.example.meatwaybackend.dto.ad.bird;

import com.example.meatwaybackend.dto.SaveRequestFileDto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record BirdAdSaveRequest(
        String meatType, //убрать
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
        String birdType,
        Boolean hasMedicalCertificate,
        List<SaveRequestFileDto> files
) {
}