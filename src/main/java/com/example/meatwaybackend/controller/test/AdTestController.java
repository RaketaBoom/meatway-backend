package com.example.meatwaybackend.controller.test;

import com.example.meatwaybackend.controller.order.OptOrderController;
import com.example.meatwaybackend.dto.SaveRequestFileDto;
import com.example.meatwaybackend.dto.ad.beef.BeefAdSaveRequest;
import com.example.meatwaybackend.dto.ad.bird.BirdAdSaveRequest;
import com.example.meatwaybackend.dto.ad.pork.PorkAdSaveRequest;
import com.example.meatwaybackend.dto.ad.sheepmeat.SheepmeatAdSaveRequest;
import com.example.meatwaybackend.dto.ad.specialmeat.SpecialmeatAdSaveRequest;
import com.example.meatwaybackend.dto.register.UserCreateDTO;
import com.example.meatwaybackend.service.RegistrationService;
import com.example.meatwaybackend.service.ad.BeefService;
import com.example.meatwaybackend.service.ad.BirdService;
import com.example.meatwaybackend.service.ad.PorkService;
import com.example.meatwaybackend.service.ad.SheepmeatService;
import com.example.meatwaybackend.service.ad.SpecialmeatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
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
        // Создаем тестовых пользователей
        UserCreateDTO userPavel = new UserCreateDTO("pavel@mail.ru", "11111111", "11111111");
        UserCreateDTO userMyasnik = new UserCreateDTO("myasnik@mail.ru", "11111111", "11111111");
        UserCreateDTO userMclovin = new UserCreateDTO("mclovin@mail.ru", "11111111", "11111111");



        registrationService.create(userPavel);
        registrationService.create(userMyasnik);
        registrationService.create(userMclovin);

        // Загружаем тестовые объявления
        loadBeefAds(userMyasnik);
        loadPorkAds(userMyasnik);
        loadSheepmeatAds(userMyasnik);
        loadBirdAds(userMyasnik);
        loadSpecialmeatAds(userMyasnik);

        return "success";
    }

    private void loadBeefAds(UserCreateDTO user) {
        // Говядина 1
        BeefAdSaveRequest beef1 = new BeefAdSaveRequest(
                null,
                "Говядина высшего сорта, фермерская",
                "Свежая говядина от проверенного производителя. Мраморная текстура, без консервантов.",
                new BigDecimal("780"),
                "Абердин-ангус",
                24,
                1200, // 1.2 кг в граммах
                50,
                "Москва",
                false,
                true,
                null,
                null,
                null,
                false,
                true,
                true,
                null,
                null,
                List.of(
                        new SaveRequestFileDto("test-ad-beef-1-1photo.jpg"),
                        new SaveRequestFileDto("test-ad-beef-2-1photo.jpg")
                )
        );

        // Говядина 2
        BeefAdSaveRequest beef2 = new BeefAdSaveRequest(
                null,
                "Замороженная говядина на кости",
                "Мясные блоки на кости, идеально для шашлыков и бульонов.",
                new BigDecimal("520"),
                "Симментальская",
                30,
                2500, // 2.5 кг в граммах
                200,
                "Казань",
                true,
                false,
                null,
                null,
                null,
                true,
                false,
                false,
                null,
                null,
                List.of(
                        new SaveRequestFileDto("test-ad-beef-2-2photo.jpg"),
                        new SaveRequestFileDto("test-ad-beef-2-3photo.jpg")
                )
        );

        beefService.createBeefAd(beef1, user.email());
        beefService.createBeefAd(beef2, user.email());
    }

    private void loadPorkAds(UserCreateDTO user) {
        // Свинина 1
        PorkAdSaveRequest pork1 = new PorkAdSaveRequest(
                null,
                "Свинина фермерская, свежая",
                "Фермерская свинина без гормонов, экологически чистое мясо.",
                new BigDecimal("580"),
                "Ландрас",
                8,
                2100, // 2.1 кг в граммах
                80,
                "Самара",
                false,
                true,
                null,
                null,
                null,
                true,
                null,
                null,
                List.of(
                        new SaveRequestFileDto("test-ad-pork-1-1photo.jpg"),
                        new SaveRequestFileDto("test-ad-pork-2-1photo.jpg")
                )
        );

        // Свинина 2
        PorkAdSaveRequest pork2 = new PorkAdSaveRequest(
                null,
                "Замороженное мясо свинины оптом",
                "Крупный опт. Замороженные блоки свинины для перепродажи.",
                new BigDecimal("480"),
                null,
                null,
                5000, // 5.0 кг в граммах
                1,
                "Краснодар",
                true,
                false,
                null,
                null,
                null,
                true,
                null,
                null,
                List.of()
        );

        porkService.createPorkAd(pork1, user.email());
        porkService.createPorkAd(pork2, user.email());
    }

    private void loadSheepmeatAds(UserCreateDTO user) {
        // Баранина 1
        SheepmeatAdSaveRequest sheepmeat1 = new SheepmeatAdSaveRequest(
                null,
                "Баранина свежая, молодого ягнёнка",
                "Свежая баранина, мясо молодого ягнёнка, без запаха, нежное.",
                new BigDecimal("850"),
                "Эдильбаевский",
                6,
                1500, // 1.5 кг в граммах
                30,
                "Челябинск",
                false,
                true,
                null,
                null,
                null,
                true,
                true,
                null,
                null,
                List.of(
                        new SaveRequestFileDto("test-ad-sheepmeat-1-1photo.jpg"),
                        new SaveRequestFileDto("test-ad-sheepmeat-2-1photo.jpg")
                )
        );

        // Баранина 2
        SheepmeatAdSaveRequest sheepmeat2 = new SheepmeatAdSaveRequest(
                null,
                "Замороженная оленина",
                "Замороженное мясо ягнят, отличное качество, сертифицировано.",
                new BigDecimal("750"),
                "Северный ягнёнок",
                8,
                3000, // 3.0 кг в граммах
                200,
                "Якутск",
                true,
                false,
                null,
                null,
                null,
                true,
                true,
                null,
                null,
                List.of(
                        new SaveRequestFileDto("test-ad-sheepmeat-2-2photo.jpg"),
                        new SaveRequestFileDto("test-ad-sheepmeat-2-3photo.jpg")
                )
        );

        sheepmeatService.createSheepmeatAd(sheepmeat1, user.email());
        sheepmeatService.createSheepmeatAd(sheepmeat2, user.email());
    }

    private void loadBirdAds(UserCreateDTO user) {
        // Птица 1
        BirdAdSaveRequest bird1 = new BirdAdSaveRequest(
                null,
                "Цыпленок бройлер свежий",
                "Свежее мясо цыпленка бройлера, без химии, домашнее выращивание.",
                new BigDecimal("280"),
                "РОБ РОСС 308",
                2,
                1800, // 1.8 кг в граммах
                100,
                "Нижний Новгород",
                false,
                true,
                null,
                null,
                null,
                "Бройлер",
                true,
                List.of(
                        new SaveRequestFileDto("test-ad-bird-1-1photo.jpg"),
                        new SaveRequestFileDto("test-ad-bird-2-1photo.jpg")
                )
        );

        // Птица 2
        BirdAdSaveRequest bird2 = new BirdAdSaveRequest(
                null,
                "Индейка замороженная, охлаждённая",
                "Охлаждённое мясо индейки, подходит для праздника и диетического питания.",
                new BigDecimal("350"),
                "Биг-6",
                5,
                3200, // 3.2 кг в граммах
                150,
                "Ростов-на-Дону",
                true,
                false,
                null,
                null,
                null,
                "Индейка",
                true,
                List.of(
                        new SaveRequestFileDto("test-ad-bird-2-2photo.jpg"),
                        new SaveRequestFileDto("test-ad-bird-2-3photo.jpg")
                )
        );

        birdService.createBirdAd(bird1, user.email());
        birdService.createBirdAd(bird2, user.email());
    }

    private void loadSpecialmeatAds(UserCreateDTO user) {
        // Спец мясо 1
        SpecialmeatAdSaveRequest specialmeat1 = new SpecialmeatAdSaveRequest(
                null,
                "Лосятина свежезамороженная, охотничья",
                "Мясо молодого лося, добыто в экологически чистом районе. Подходит для запекания и гриля.",
                new BigDecimal("1500"),
                null,
                null,
                2300, // 2.3 кг в граммах
                100,
                "Иркутская область",
                true,
                false,
                null,
                null,
                null,
                false,
                true,
                null,
                null,
                List.of(
                        new SaveRequestFileDto("test-ad-specialmeat-1-1photo.jpg"),
                        new SaveRequestFileDto("test-ad-specialmeat-1-2photo.jpg")
                )
        );

        // Спец мясо 2
        SpecialmeatAdSaveRequest specialmeat2 = new SpecialmeatAdSaveRequest(
                null,
                "Филе лося, порционное, вакуум",
                "Порционные куски филе лося, упакованы в вакуум, отличное качество.",
                new BigDecimal("1800"),
                null,
                null,
                1000, // 1.0 кг в граммах
                50,
                "Якутия",
                true,
                true,
                null,
                null,
                null,
                false,
                true,
                null,
                null,
                List.of(
                        new SaveRequestFileDto("test-ad-specialmeat-2-1photo.jpg"),
                        new SaveRequestFileDto("test-ad-specialmeat-2-2photo.jpg")
                )
        );

        specialmeatService.createSpecialmeatAd(specialmeat1, user.email());
        specialmeatService.createSpecialmeatAd(specialmeat2, user.email());
    }
}