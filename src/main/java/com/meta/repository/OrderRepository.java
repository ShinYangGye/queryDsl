package com.meta.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meta.dto.res.OrderResDto;
import com.meta.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select distinct o from Order o"
			+ " join fetch o.member m"
			+ " join fetch o.orderItems oi"
			+ " join fetch oi.item i")
	public List<Order> findAllOderFetchJoin1();
	
	@Query("select distinct o from Order o"
			+ " join fetch o.member m"
			+ " join fetch o.orderItems oi"
			+ " join fetch oi.item i")
	public List<OrderResDto> findAllOrderResDto();
	
	@Query("select distinct o from Order o"
			+ " join fetch o.member m")
	public List<Order> findAllOderFetchJoin3(Pageable pageable);
	
}
