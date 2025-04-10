package com.example.meatwaybackend.mapper;

import com.example.meatwaybackend.dto.order.opt.OptOrderCreateRequest;
import com.example.meatwaybackend.dto.order.opt.OptOrderCreateResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrderEditRequest;
import com.example.meatwaybackend.dto.order.opt.OptOrderResponse;
import com.example.meatwaybackend.dto.order.opt.OptOrdersResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateRequest;
import com.example.meatwaybackend.dto.order.retail.RetailOrderCreateResponse;
import com.example.meatwaybackend.dto.order.retail.RetailOrderEditRequest;
import com.example.meatwaybackend.dto.order.retail.RetailOrderResponse;
import com.example.meatwaybackend.model.order.OptOrder;
import com.example.meatwaybackend.model.order.RetailOrder;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderMapper {
    OptOrderResponse OptOrderToOptOrderResponse(OptOrder optOrder);

    List<OptOrderResponse> toListOptOrdersResponse(List<OptOrder> optOrders);

    OptOrderCreateResponse OptOrderToOptOrderCreateResponse(OptOrder save);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OptOrder updateOptOrderFromOptOrderEditRequest(@MappingTarget OptOrder optOrder, OptOrderEditRequest optOrderEditRequest);

    RetailOrderResponse RetailOrderToRetailOrderResponse(RetailOrder retailOrder);

    List<RetailOrderResponse> toListRetailOrdersResponse(List<RetailOrder> retailOrders);

    RetailOrderCreateResponse RetailOrderToRetailOrderCreateResponse(RetailOrder save);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RetailOrder updateRetailOrderFromRetailOrderEditRequest(@MappingTarget RetailOrder retailOrder, RetailOrderEditRequest request);
}
