package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdResponse;
import com.example.meatwaybackend.dto.ad.beef.BeefAdSaveRequest;
import com.example.meatwaybackend.dto.ad.beef.BeefAdsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = BeefController.BEEF_CONTROLLER, description = "API Объявлений говядины")
@RequestMapping(BeefController.API_AD)
public class BeefController {
    public static final String BEEF_CONTROLLER = "beef-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + "/beefs";

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
        //TODO
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public BeefAdResponse findById(@PathVariable int id) {
        //TODO
        return null;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public BeefAdResponse createById(
            @PathVariable int id,
            @RequestBody BeefAdSaveRequest request
            ) {
        //TODO
        return null;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public BeefAdResponse editById(
            @PathVariable int id,
            @RequestBody BeefAdSaveRequest request
    ) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление c говядиной",
            tags = {BEEF_CONTROLLER}
    )
    public void deleteById(
            @PathVariable int id
    ) {
        //TODO
    }
}