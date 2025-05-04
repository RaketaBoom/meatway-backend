package com.example.meatwaybackend.controller.order;


import com.example.meatwaybackend.dto.order.opt.OptOrderCreateRequest;
import com.example.meatwaybackend.dto.order.opt.OptOrderCreateResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrderResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrdersResponse;
import com.example.meatwaybackend.service.order.OptOrderService;
import com.example.meatwaybackend.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
@RequiredArgsConstructor
public class OptOrderController {
    public static final String OPT_ORDER_CONTROLLER = "opt-order-controller";
    static final String API_VERSION = "v1";
    static final String API_PREFIX = "/api/" + API_VERSION;
    public static final String API_ORDER = API_PREFIX + "/orders/opt";

    private final OptOrderService optOrderService;
    private final JWTUtils jwtUtils;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderResponse findOrderById(@PathVariable Long id) {
        return optOrderService.findById(id);
    }

    @GetMapping("/ad/{adId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить оптовый заказы по id объявления",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrdersResponse findOrdersByAd(@PathVariable long adId) {
        return optOrderService.findByAdId(adId);
    }

    @GetMapping("/buyer/{buyerId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Получить оптовый заказы по id покупателя",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrdersResponse findOrdersByBuyer(@PathVariable long buyerId) {
        return optOrderService.findByBuyerId(buyerId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Создать оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse createOrder(@RequestBody OptOrderCreateRequest request) {
        return optOrderService.createOrder(request);
    }

    @PatchMapping("/confirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Подтвердить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse confirmOrder(@PathVariable long id, @AuthenticationPrincipal Jwt jwt) {
        return optOrderService.confirmOrder(id, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @PatchMapping("/unconfirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Отменить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse unconfirmOrder(@PathVariable long id, @AuthenticationPrincipal Jwt jwt) {
        return optOrderService.unconfirmOrder(id, jwtUtils.extractUsername(jwt.getTokenValue()));
    }

    @PatchMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Активировать оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse activateOrder(@PathVariable long id) {
        return optOrderService.activateOrder(id);
    }

    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Деактиваровать оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public OptOrderCreateResponse deactivateOrder(@PathVariable long id) {
        return optOrderService.deactivateOrder(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Удалить оптовый заказ",
            tags = {OPT_ORDER_CONTROLLER}
    )
    public void deleteOrder(@PathVariable long id) {
        optOrderService.removeOrder(id);
    }
}