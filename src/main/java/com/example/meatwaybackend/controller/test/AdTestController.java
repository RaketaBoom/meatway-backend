package com.example.meatwaybackend.controller.test;

import com.example.meatwaybackend.controller.order.OptOrderController;
import com.example.meatwaybackend.dto.SaveRequestFileDto;
import com.example.meatwaybackend.dto.ad.beef.BeefAdSaveRequest;
import com.example.meatwaybackend.dto.ad.pork.PorkAdSaveRequest;
import com.example.meatwaybackend.dto.register.UserCreateDTO;
import com.example.meatwaybackend.service.RegistrationService;
import com.example.meatwaybackend.service.ad.BeefService;
import com.example.meatwaybackend.service.ad.BirdService;
import com.example.meatwaybackend.service.ad.PorkService;
import com.example.meatwaybackend.service.ad.SheepmeatService;
import com.example.meatwaybackend.service.ad.SpecialmeatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = OptOrderController.OPT_ORDER_CONTROLLER, description = "API Оптовых заказов")
@RequestMapping("api/v1/test")
@RequiredArgsConstructor
public class AdTestController {
    private final RegistrationService registrationService;
    private final BeefService beefService;
    private final BirdService birdService;
    private final PorkService porkService;
    private final SheepmeatService sheepmeatService;
    private final SpecialmeatService specialmeatService;

    @PostMapping("/loaddata")
    @ResponseStatus(HttpStatus.CREATED)
    public String loaddata() {
        UserCreateDTO userPavel = new UserCreateDTO("pavel@mail.ru", "11111111", "12345678");
        UserCreateDTO userMyasnik = new UserCreateDTO("myasnik@mail.ru", "11111111", "12345678");
        UserCreateDTO userMclovin = new UserCreateDTO("mclovin@mail.ru", "11111111", "12345678");

        registrationService.create(userPavel);
        registrationService.create(userMyasnik);
        registrationService.create(userMclovin);

        loadBeefsFor(userMyasnik);
        loadPorksFor(userMyasnik);

        return "success";
    }

    private void loadPorksFor(UserCreateDTO user) {
        // 1. Полный вариант (премиум свинина)
        PorkAdSaveRequest premiumPork = new PorkAdSaveRequest(
                "Свинина мраморная",
                "Отборная свинина с равномерной жировой прослойкой",
                new BigDecimal("890.99"),
                "Дюрок",
                12,
                110,
                8,
                "Белгородская область",
                false,
                true,
                new Date(),
                new Date(System.currentTimeMillis() + 86400000L * 14),
                new Date(System.currentTimeMillis() - 86400000L * 2),
                true,
                List.of(
                        new SaveRequestFileDto("/uploads/pork1.jpg"),
                        new SaveRequestFileDto("/uploads/certificate.pdf")
                )
        );

// 2. Замороженная оптовая партия
        PorkAdSaveRequest frozenWholesale = new PorkAdSaveRequest(
                "Замороженная свинина оптом",
                "Партия 50 кг, вакуумная упаковка",
                new BigDecimal("450.00"),
                "Ландрас",
                10,
                50,
                1, // 1 партия
                "Тюмень",
                true,
                false,
                new Date(),
                new Date(System.currentTimeMillis() + 86400000L * 180),
                new Date(System.currentTimeMillis() - 86400000L * 30),
                true,
                List.of(new SaveRequestFileDto("/uploads/frozen_pork.jpg"))
        );

// 3. Минимальный вариант (розница)
        PorkAdSaveRequest minimalRetail = new PorkAdSaveRequest(
                "Свиная шея",
                null,
                new BigDecimal("650.50"),
                null,
                null,
                null,
                1,
                "Москва",
                false,
                true,
                new Date(),
                null,
                null,
                false,
                List.of()
        );

        porkService.createPorkAd(premiumPork, user.email());
        porkService.createPorkAd(frozenWholesale, user.email());
        porkService.createPorkAd(minimalRetail, user.email());
    }

    private void loadBeefsFor(UserCreateDTO user) {
        BeefAdSaveRequest beef1 = new BeefAdSaveRequest(
                "Премиальная говядина Black Angus",
                "Свежая мраморная говядина высшего качества, сухой созревания 21 день",
                new BigDecimal("1250.99"),
                "Black Angus",
                24,
                200,
                15,
                "Краснодарский край",
                false,
                true,
                null,  // через 7 дней
                null, // через 30 дней
                new Date(System.currentTimeMillis() + 86400000L * 7),
                true,
                true,
                true,
                List.of(
                        new SaveRequestFileDto("/uploads/angus1.jpg"),
                        new SaveRequestFileDto("/uploads/angus2.jpg")
                )
        );

        BeefAdSaveRequest beef2 = new BeefAdSaveRequest(
                "Говядина фермерская",
                null,  // описание не обязательно
                new BigDecimal("850.50"),
                null,  // порода не указана
                null,  // возраст не указан
                null,  // вес не указан
                1,     // минимальное количество
                "Ленинградская область",
                false,
                false,
                null,
                null,
                null,
                false,
                false,
                false,
                List.of()    // нет файлов
        );
        BeefAdSaveRequest beef3 = new BeefAdSaveRequest(
                "Премиальная говядина Black Angus",
                "Свежая мраморная говядина высшего качества, сухой созревания 21 день",
                new BigDecimal("1250.99"),
                "Black Angus",
                24,
                200,
                15,
                "Краснодарский край",
                false,
                false,
                new Date(System.currentTimeMillis() + 86400000L * 7),  // через 7 дней
                new Date(System.currentTimeMillis() + 86400000L * 30), // через 30 дней
                null,
                true,
                true,
                true,
                List.of(
                        new SaveRequestFileDto("/uploads/angus1.jpg"),
                        new SaveRequestFileDto("/uploads/angus2.jpg")
                )
        );

        beefService.createBeefAd(beef1, user.email());
        beefService.createBeefAd(beef2, user.email());
        beefService.createBeefAd(beef3, user.email());
    }
}
