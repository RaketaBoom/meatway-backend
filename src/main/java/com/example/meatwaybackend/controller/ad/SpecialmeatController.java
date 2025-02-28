package com.example.meatwaybackend.controller.ad;

import com.example.meatwaybackend.dto.ad.ShortAdsResponse;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdResponse;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdSaveRequest;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = SpecialmeatController.CONTROLLER, description = "API Объявлений специального мяса")
@RequestMapping(SpecialmeatController.API_AD)
public class SpecialmeatController {
    public static final String CONTROLLER = "specialmeat-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_AD = API_PREFIX + "/specialmeats";

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
        //TODO
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Вывести информацию объявления cо специальным мясом",
            tags = {CONTROLLER}
    )
    public SpecialmeatAdResponse findById(@PathVariable int id) {
        //TODO
        return null;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать объявление cо специальным мясом",
            tags = {CONTROLLER}
    )
    public SpecialmeatAdResponse createById(
            @PathVariable int id,
            @RequestBody SpecialmeatAdSaveRequest request
    ) {
        //TODO
        return null;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Редактировать объявление cо специальным мясом",
            tags = {CONTROLLER}
    )
    public SpecialmeatAdResponse editById(
            @PathVariable int id,
            @RequestBody SpecialmeatAdSaveRequest request
    ) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить объявление cо специальным мясом",
            tags = {CONTROLLER}
    )
    public void deleteById(
            @PathVariable int id
    ) {
        //TODO
    }
}