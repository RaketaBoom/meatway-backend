package com.example.meatwaybackend.service.order;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dao.ad.AdvertisementRepository;
import com.example.meatwaybackend.dao.order.RetailOrderRepository;
import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateRequest;
import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrderEditRequest;
import com.example.meatwaybackend.dto.order.retail.RetailOrderResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrdersResponse;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.OrderMapper;
import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.order.RetailOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetailOrderService {
    private final RetailOrderRepository retailOrderRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public RetailOrderResponse findById(Long id) {
        RetailOrder retailOrder = retailOrderRepository.findById(id).orElseThrow(() -> new NotFoundException(RetailOrder.class, id));

        return orderMapper.RetailOrderToRetailOrderResponse(retailOrder);
    }

    public RetailOrdersResponse findByAdId(long adId) {
        Advertisement ad = advertisementRepository.findById(adId).orElseThrow(() -> new NotFoundException(Advertisement.class, adId));

        return new RetailOrdersResponse(
                orderMapper.toListRetailOrdersResponse(ad.getRetailOrders()),
                ad.getRetailOrders().size()
        );
    }

    public RetailOrdersResponse findByBuyerId(long buyerId) {
        User user = userRepository.findById(buyerId).orElseThrow(() -> new NotFoundException(User.class, buyerId));

        return new RetailOrdersResponse(
                orderMapper.toListRetailOrdersResponse(user.getRetailOrders()),
                user.getRetailOrders().size()
        );
    }

    public RetailOrderCreateResponse createOrder(RetailOrderCreateRequest request) {
        RetailOrder retailOrder = orderMapper.toRetailOrder(request);

        RetailOrder retailOrder1 = retailOrderRepository.save(retailOrder);

        return orderMapper.RetailOrderToRetailOrderCreateResponse(retailOrder1);
    }

    public RetailOrderCreateResponse confirmOrder(long id) {
        RetailOrderEditRequest request = new RetailOrderEditRequest(
                null,
                true,
                null
        );

        return patchOrder(id, request);
    }

    public RetailOrderCreateResponse unconfirmOrder(long id) {
        RetailOrderEditRequest request = new RetailOrderEditRequest(
                null,
                false,
                null
        );

        return patchOrder(id, request);
    }

    public RetailOrderCreateResponse activateOrder(long id) {
        RetailOrderEditRequest request = new RetailOrderEditRequest(
                null,
                null,
                true
        );

        return patchOrder(id, request);
    }

    public RetailOrderCreateResponse deactivateOrder(long id) {
        RetailOrderEditRequest request = new RetailOrderEditRequest(
                null,
                null,
                false
        );

        return patchOrder(id, request);
    }

    private RetailOrderCreateResponse patchOrder(long id, RetailOrderEditRequest request) {
        RetailOrder retailOrder = retailOrderRepository.findById(id).orElseThrow(() -> new NotFoundException(RetailOrder.class, id));
        orderMapper.updateRetailOrderFromRetailOrderEditRequest(retailOrder, request);

        return orderMapper.RetailOrderToRetailOrderCreateResponse(retailOrderRepository.save(retailOrder));
    }

    public void removeOrder(long id) {
        retailOrderRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        retailOrderRepository.deleteById(id);
    }
}