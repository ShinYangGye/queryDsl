package com.meta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meta.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
