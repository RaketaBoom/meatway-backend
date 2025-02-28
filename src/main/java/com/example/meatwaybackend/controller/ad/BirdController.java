package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.bird.BirdAdResponse;
import com.example.meatwaybackend.dto.ad.bird.BirdAdSaveRequest;
import com.example.meatwaybackend.dto.ad.bird.BirdAdsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = BirdController.BIRD_CONTROLLER, description = "API Объявлений птицы")
@RequestMapping(BirdController.API_AD)
public class BirdController {
    public static final String BIRD_CONTROLLER = "bird-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + "/birds";

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort,
            @RequestBody BirdAdsRequest request
    ) {
        //TODO
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public BirdAdResponse findById(@PathVariable int id) {
        //TODO
        return null;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public BirdAdResponse createById(
            @PathVariable int id,
            @RequestBody BirdAdSaveRequest request
    ) {
        //TODO
        return null;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public BirdAdResponse editById(
            @PathVariable int id,
            @RequestBody BirdAdSaveRequest request
    ) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление c птицей",
            tags = {BIRD_CONTROLLER}
    )
    public void deleteById(
            @PathVariable int id
    ) {
        //TODO
    }
}