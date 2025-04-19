package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdSaveRequest;
import com.example.meatwaybackend.dto.ad.beef.BeefAdsRequest;
import com.example.meatwaybackend.service.ad.BeefService;
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
@Tag(name = BeefController.BEEF_CONTROLLER, description = "API Объявлений говядины")
@RequestMapping(BeefController.API_AD)
@RequiredArgsConstructor
public class BeefController {
    public static final String BEEF_CONTROLLER = "beef-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + AdvertisementController.ADS_PREFIX + "/beefs";

    private final BeefService beefService;
    private final JWTUtils jwtUtils;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort,
            @RequestBody BeefAdsRequest request
    ) {
        return beefService.findAll(page, size, sort, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public BeefAdResponse findById(@PathVariable int id) {
        return beefService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public BeefAdResponse createBeef(
            @RequestBody BeefAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return beefService.createBeefAd(request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public BeefAdResponse editById(
            @PathVariable int id,
            @RequestBody BeefAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return beefService.patchById(id, request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public void deleteById(
            @PathVariable int id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        beefService.deleteById(id, jwtUtils.extractUsername(jwt.getTokenValue()));
    }
}