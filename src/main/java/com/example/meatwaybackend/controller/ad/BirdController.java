package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.bird.BirdAdResponse;
import com.example.meatwaybackend.dto.ad.bird.BirdAdSaveRequest;
import com.example.meatwaybackend.dto.ad.bird.BirdAdsRequest;
import com.example.meatwaybackend.service.ad.BirdService;
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
@Tag(name = BirdController.BIRD_CONTROLLER, description = "API Объявлений птицы")
@RequestMapping(BirdController.API_AD)
@RequiredArgsConstructor
public class BirdController {
    public static final String BIRD_CONTROLLER = "bird-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + AdvertisementController.ADS_PREFIX + "/birds";

    private final BirdService birdService;
    private final JWTUtils jwtUtils;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort,
            @RequestBody(required = false) BirdAdsRequest request
    ) {
        return birdService.findAll(page, size, sort, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public BirdAdResponse findById(@PathVariable int id) {
        return birdService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public BirdAdResponse createBird(
            @RequestBody BirdAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return birdService.createBirdAd(request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public BirdAdResponse editById(
            @PathVariable int id,
            @RequestBody BirdAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return birdService.patchById(id, request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public void deleteById(
            @PathVariable long id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        birdService.deleteById(id, jwtUtils.extractUsername(jwt.getTokenValue()));
    }
}