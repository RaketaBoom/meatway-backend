package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.AdsRequest;
import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.service.ad.AdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = AdvertisementController.AD_CONTROLLER, description = "API Объявлений")
@RequestMapping(AdvertisementController.API_AD)
@RequiredArgsConstructor
public class AdvertisementController {
    public static final String AD_CONTROLLER = "ad-controller";
    static final String API_VERSION = "v3";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + "/ads";

    private final AdService adService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях",
            tags = {AD_CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) String sort,
            @RequestBody(required = false) AdsRequest request
    ) {
        return adService.findAll(page, size, sort, request);
    }
}
