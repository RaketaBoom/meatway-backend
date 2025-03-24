package com.example.meatwaybackend.dao.order;

import com.example.meatwaybackend.model.order.OptOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptOrderRepository extends JpaRepository<OptOrder, Long> {
}
