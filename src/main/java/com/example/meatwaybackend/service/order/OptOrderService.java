package com.example.meatwaybackend.service.order;

import com.example.meatwaybackend.dao.UserRepository;
import com.example.meatwaybackend.dao.ad.AdvertisementRepository;
import com.example.meatwaybackend.dao.order.OptOrderRepository;
import com.example.meatwaybackend.dto.order.opt.OptOrderCreateRequest;
import com.example.meatwaybackend.dto.order.opt.OptOrderCreateResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrderEditRequest;
import com.example.meatwaybackend.dto.order.opt.OptOrderResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrdersResponse;
import com.example.meatwaybackend.handler.exception.user.NotFoundException;
import com.example.meatwaybackend.mapper.OrderMapper;
import com.example.meatwaybackend.model.User;
import com.example.meatwaybackend.model.ad.Advertisement;
import com.example.meatwaybackend.model.order.OptOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptOrderService {
    private final OptOrderRepository optOrderRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;


    public OptOrderResponse findById(Long id) {
        OptOrder optOrder = optOrderRepository.findById(id).orElseThrow(() -> new NotFoundException(OptOrder.class, id));
        
        return orderMapper.OptOrderToOptOrderResponse(optOrder);
    }


    public OptOrdersResponse findByAdId(long adId) {
        Advertisement ad = advertisementRepository.findById(adId).orElseThrow(() -> new NotFoundException(Advertisement.class, adId));

        return new OptOrdersResponse(
                orderMapper.toListOptOrdersResponse(ad.getOptOrders()),
                ad.getOptOrders().size()
        );
    }


    public OptOrdersResponse findByBuyerId(long buyerId) {
        User user = userRepository.findById(buyerId).orElseThrow(() -> new NotFoundException(User.class, buyerId));

        return new OptOrdersResponse(
                orderMapper.toListOptOrdersResponse(user.getOptOrders()),
                user.getOptOrders().size()
        );
    }


    public OptOrderCreateResponse createOrder(OptOrderCreateRequest request) {
        Advertisement advertisement = advertisementRepository.findById(request.advertisementId())
                .orElseThrow(() -> new NotFoundException(Advertisement.class, request.advertisementId()));
        User buyer = userRepository.findById(request.buyerUserId())
                .orElseThrow(() -> new NotFoundException(User.class, request.buyerUserId()));
        OptOrder optOrder = optOrderRepository.save(
                OptOrder
                        .builder()
                        .advertisement(advertisement)
                        .buyerUser(buyer)
                        .quantity(request.quantity())
                        .killDate(request.killDate())
                        .build()
        );

        return orderMapper.OptOrderToOptOrderCreateResponse(optOrder);
    }

    public OptOrderCreateResponse confirmOrder(long id, String email) {
        //todo реализоавть подтверждение для продавцов

        OptOrderEditRequest request = new OptOrderEditRequest(
                null,
                null,
                true,
                null
        );

        return patchOrder(id, request);
    }

    public OptOrderCreateResponse unconfirmOrder(long id, String email) {
        //todo реализовать отмену для продавцов и покупателей

        OptOrderEditRequest request = new OptOrderEditRequest(
                null,
                null,
                false,
                null
        );

        return patchOrder(id, request);
    }

    public OptOrderCreateResponse activateOrder(long id) {
        OptOrderEditRequest request = new OptOrderEditRequest(
                null,
                null,
                null,
                true
        );

        return patchOrder(id, request);
    }

    public OptOrderCreateResponse deactivateOrder(long id) {
        OptOrderEditRequest request = new OptOrderEditRequest(
                null,
                null,
                null,
                false
        );

        return patchOrder(id, request);
    }



    private OptOrderCreateResponse patchOrder(long id, OptOrderEditRequest request) {
        OptOrder optOrder = optOrderRepository.findById(id).orElseThrow(() -> new NotFoundException(OptOrder.class, id));
        OptOrder newOrder = orderMapper.updateOptOrderFromOptOrderEditRequest(optOrder, request);

        return orderMapper.OptOrderToOptOrderCreateResponse(optOrderRepository.save(newOrder));
    }

    public void removeOrder(long id) {
        optOrderRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        optOrderRepository.deleteById(id);
    }
}
