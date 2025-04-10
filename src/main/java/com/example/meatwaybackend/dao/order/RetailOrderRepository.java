package com.example.meatwaybackend.dao.order;

import com.example.meatwaybackend.model.order.RetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailOrderRepository extends JpaRepository<RetailOrder, Long> {
}
