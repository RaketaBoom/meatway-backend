package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdSaveRequest;
import com.example.meatwaybackend.dto.ad.pork.PorkAdsRequest;
import com.example.meatwaybackend.service.ad.PorkService;
import com.example.meatwaybackend.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = PorkController.CONTROLLER, description = "API Объявлений свинины")
@RequestMapping(PorkController.API_AD)
@RequiredArgsConstructor
public class PorkController {
    public static final String CONTROLLER = "pork-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + AdvertisementController.ADS_PREFIX + "/porks";

    private final PorkService porkService;
    private final JWTUtils jwtUtils;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях cо свининой",
            tags = {CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort,
            @RequestBody(required = false) PorkAdsRequest request
    ) {
        return porkService.findAll(page, size, sort, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления cо свининой",
            tags = {CONTROLLER}
    )
    public PorkAdResponse findById(@PathVariable int id) {
        return porkService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление cо свининой",
            tags = {CONTROLLER}
    )
    public PorkAdResponse createPork(
            @RequestBody PorkAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return porkService.createPorkAd(request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление cо свининой",
            tags = {CONTROLLER}
    )
    public PorkAdResponse editById(
            @PathVariable int id,
            @RequestBody PorkAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return porkService.patchById(id, request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление cо свининой",
            tags = {CONTROLLER}
    )
    public void deleteById(
            @PathVariable int id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        porkService.deleteById(id, jwtUtils.extractUsername(jwt.getTokenValue()));
    }
}