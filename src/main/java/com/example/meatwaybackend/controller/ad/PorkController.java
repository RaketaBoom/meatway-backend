package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdResponse;
import com.example.meatwaybackend.dto.ad.pork.PorkAdSaveRequest;
import com.example.meatwaybackend.dto.ad.pork.PorkAdsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = PorkController.CONTROLLER, description = "API Объявлений свинины")
@RequestMapping(PorkController.API_AD)
public class PorkController {
    public static final String CONTROLLER = "pork-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + "/porks";

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях cо свининой",
            tags = {CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort,
            @RequestBody PorkAdsRequest request
    ) {
        //TODO
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления cо свининой",
            tags = {CONTROLLER}
    )
    public PorkAdResponse findById(@PathVariable int id) {
        //TODO
        return null;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление cо свининой",
            tags = {CONTROLLER}
    )
    public PorkAdResponse createById(
            @PathVariable int id,
            @RequestBody PorkAdSaveRequest request
    ) {
        //TODO
        return null;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление cо свининой",
            tags = {CONTROLLER}
    )
    public PorkAdResponse editById(
            @PathVariable int id,
            @RequestBody PorkAdSaveRequest request
    ) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление cо свининой",
            tags = {CONTROLLER}
    )
    public void deleteById(
            @PathVariable int id
    ) {
        //TODO
    }
}