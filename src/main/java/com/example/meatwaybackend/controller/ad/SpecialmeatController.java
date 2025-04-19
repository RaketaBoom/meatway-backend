package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdResponse;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdSaveRequest;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdsRequest;
import com.example.meatwaybackend.service.ad.SpecialmeatService;
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
@Tag(name = SpecialmeatController.CONTROLLER, description = "API Объявлений специального мяса")
@RequestMapping(SpecialmeatController.API_AD)
@RequiredArgsConstructor
public class SpecialmeatController {
    public static final String CONTROLLER = "specialmeat-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + AdvertisementController.ADS_PREFIX + "/specialmeats";

    private final SpecialmeatService specialmeatService;
    private final JWTUtils jwtUtils;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях cо специальным мясом",
            tags = {CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort,
            @RequestBody SpecialmeatAdsRequest request
    ) {
        return specialmeatService.findAll(page, size, sort, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления cо специальным мясом",
            tags = {CONTROLLER}
    )
    public SpecialmeatAdResponse findById(@PathVariable int id) {
        return specialmeatService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление cо специальным мясом",
            tags = {CONTROLLER}
    )
    public SpecialmeatAdResponse createById(
            @RequestBody SpecialmeatAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return specialmeatService.createSpecialmeatAd(request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление cо специальным мясом",
            tags = {CONTROLLER}
    )
    public SpecialmeatAdResponse editById(
            @PathVariable long id,
            @RequestBody SpecialmeatAdSaveRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return specialmeatService.patchById(id, request, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление cо специальным мясом",
            tags = {CONTROLLER}
    )
    public void deleteById(
            @PathVariable long id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        specialmeatService.deleteById(id, jwtUtils.extractUsername(jwt.getTokenValue()));
    }
}