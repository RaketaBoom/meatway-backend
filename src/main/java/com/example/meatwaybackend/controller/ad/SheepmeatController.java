package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdResponse;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdSaveRequest;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdsRequest;
import com.example.meatwaybackend.service.ad.SheepmeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@Tag(name = SheepmeatController.SHEEPMEAT_CONTROLLER, description = "API Объявлений баранины")
@RequestMapping(SheepmeatController.API_AD)
@RequiredArgsConstructor
public class SheepmeatController {
    public static final String SHEEPMEAT_CONTROLLER = "sheepmeat-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + "/sheepmeats";

    private final SheepmeatService sheepmeatService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести краткую информацию об объявлениях c бараниной",
            tags = {SHEEPMEAT_CONTROLLER}
    )
    public ShortAdsResponse findAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sort,
            @RequestBody SheepmeatAdsRequest request
    ) {
        return sheepmeatService.findAll(page, size, sort, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления c бараниной",
            tags = {SHEEPMEAT_CONTROLLER}
    )
    public SheepmeatAdResponse findById(@PathVariable int id) {
        return sheepmeatService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление c бараниной",
            tags = {SHEEPMEAT_CONTROLLER}
    )
    public SheepmeatAdResponse createById(
            @RequestBody SheepmeatAdSaveRequest request
    ) {
        return sheepmeatService.createSheepmeatAd(request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление c бараниной",
            tags = {SHEEPMEAT_CONTROLLER}
    )
    public SheepmeatAdResponse editById(
            @PathVariable int id,
            @RequestBody SheepmeatAdSaveRequest request
    ) {
        return sheepmeatService.patchById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление c бараниной",
            tags = {SHEEPMEAT_CONTROLLER}
    )
    public void deleteById(
            @PathVariable int id
    ) {
        sheepmeatService.deleteById(id);
    }
}