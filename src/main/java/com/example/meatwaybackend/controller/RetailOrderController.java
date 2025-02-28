package com.example.meatwaybackend.controller;

import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateRequest;
import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrderResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrdersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = RetailOrderController.RETAIL_ORDER_CONTROLLER, description = "API Заказов")
@RequestMapping(RetailOrderController.API_ORDER)
public class RetailOrderController {
    public static final String RETAIL_ORDER_CONTROLLER = "retail-order-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_ORDER = API_PREFIX + "/orders/retail";

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderResponse findOrderById(@PathVariable Long id) {
        //TODO
        return null;
    }

    @GetMapping("/ad/{adId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить вразвес заказы по id объявления",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrdersResponse findOrdersByAd(@PathVariable long adId) {
        //TODO
        return null;
    }

    @GetMapping("/buyer/{buyerId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить вразвес заказы по id покупателя",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrdersResponse findOrdersByBuyer(@PathVariable long buyerId) {
        //TODO
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse createOrder(@RequestBody RetailOrderCreateRequest request) {
        //TODO
        return null;
    }

    @PatchMapping("/confirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Подтвердить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse confirmOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @PatchMapping("/unconfirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Отменить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse unconfirmOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @PatchMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Активировать вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse activateOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Деактиваровать вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse deactivateOrder(@PathVariable long id) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public void deleteOrder(@PathVariable long id) {
        //TODO
    }
}
