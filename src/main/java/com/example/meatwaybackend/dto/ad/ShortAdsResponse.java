package com.example.meatwaybackend.dto.ad;

import java.util.List;

public record ShortAdsResponse(
        List<ShortAdResponse> ads,
        int size
){
};