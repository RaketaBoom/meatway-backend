package com.example.meatwaybackend.controller;


import com.example.meatwaybackend.dto.order.opt.OptOrderCreateRequest;
import com.example.meatwaybackend.dto.order.opt.OptOrderCreateResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrderResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrdersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = OptOrderController.OPT_ORDER_CONTROLLER, description = "API Оптовых заказов")
@RequestMapping(OptOrderController.API_ORDER)
public class OptOrderController {
    public static final String OPT_ORDER_CONTROLLER = "opt-order-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_ORDER = API_PREFIX + "/orders/opt";

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderResponse findOrderById(@PathVariable Long id) {
        //TODO
        return null;
    }

    @GetMapping("/ad/{adId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить оптовый заказы по id объявления",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrdersResponse findOrdersByAd(@PathVariable long adId) {
        //TODO
        return null;
    }

    @GetMapping("/buyer/{buyerId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить оптовый заказы по id покупателя",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrdersResponse findOrdersByBuyer(@PathVariable long buyerId) {
        //TODO
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse createOrder(@RequestBody OptOrderCreateRequest request) {
        //TODO
        return null;
    }

    @PatchMapping("/confirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Подтвердить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse confirmOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @PatchMapping("/unconfirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Отменить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse unconfirmOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @PatchMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Активировать оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse activateOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Деактиваровать оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse deactivateOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public void deleteOrder(@PathVariable long id) {
        //TODO
    }
}