package com.example.meatwaybackend.controller.order;

import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateRequest;
import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrderResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrdersResponse;
import com.example.meatwaybackend.service.order.RetailOrderService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = RetailOrderController.RETAIL_ORDER_CONTROLLER, description = "API Заказов")
@RequestMapping(RetailOrderController.API_ORDER)
@RequiredArgsConstructor
public class RetailOrderController {
    public static final String RETAIL_ORDER_CONTROLLER = "retail-order-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_ORDER = API_PREFIX + "/orders/retail";

    private final RetailOrderService retailOrderService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderResponse findOrderById(@PathVariable Long id) {
        return retailOrderService.findById(id);
    }

    @GetMapping("/ad/{adId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить вразвес заказы по id объявления",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrdersResponse findOrdersByAd(@PathVariable long adId) {
        return retailOrderService.findByAdId(adId);
    }

    @GetMapping("/buyer/{buyerId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить вразвес заказы по id покупателя",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrdersResponse findOrdersByBuyer(@PathVariable long buyerId) {
        return retailOrderService.findByBuyerId(buyerId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse createOrder(@RequestBody RetailOrderCreateRequest request) {
        return retailOrderService.createOrder(request);
    }

    @PatchMapping("/confirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Подтвердить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse confirmOrder(@PathVariable long id) {
        return retailOrderService.confirmOrder(id);
    }

    @PatchMapping("/unconfirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Отменить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse unconfirmOrder(@PathVariable long id) {
        return retailOrderService.unconfirmOrder(id);
    }

    @PatchMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Активировать вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse activateOrder(@PathVariable long id) {
        return retailOrderService.activateOrder(id);
    }

    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Деактиваровать вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public RetailOrderCreateResponse deactivateOrder(@PathVariable long id) {
        return retailOrderService.deactivateOrder(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить вразвес заказ",
            tags = {RETAIL_ORDER_CONTROLLER}
    )
    public void deleteOrder(@PathVariable long id) {
        retailOrderService.removeOrder(id);
    }
}
