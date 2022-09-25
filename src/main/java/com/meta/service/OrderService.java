package com.meta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.dto.req.OrderSaveItemReqDto;
import com.meta.dto.req.OrderSaveReqDto;
import com.meta.dto.res.MemberResDto;
import com.meta.dto.res.OrderResDto;
import com.meta.entity.Delivery;
import com.meta.entity.Item;
import com.meta.entity.Member;
import com.meta.entity.Order;
import com.meta.entity.OrderItem;
import com.meta.repository.DelivertyRepository;
import com.meta.repository.ItemRepository;
import com.meta.repository.MemberRepository;
import com.meta.repository.OrderItemRepository;
import com.meta.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final DelivertyRepository delivertyRepository;
	private final OrderItemRepository orderItemRepository;
	private final ItemRepository itemRepository;
	
	private final ItemService itemService;
	
	@Transactional
	public Long saveOrder(OrderSaveReqDto reqData) {
		
		Member member = memberRepository.findById(reqData.getMemberId()).orElseThrow();
		
		Delivery delivery = Delivery.builder()
				.status("READY")
				.city(member.getCity())
				.street(member.getCity())
				.zipcode(member.getZipcode())
				.build();
		
		Order order = Order.builder()
				.status("ORDER")
				.member(member)
				.delivery(delivery)
				.build();		
				
		// 硅价瘤 历厘
		delivertyRepository.save(delivery);				
		
		// 林巩历厘
		orderRepository.save(order);
		
		// 林巩惑前 历厘
		saveOrderItems(reqData.getOrderItems(), order.getId());		
		
		return order.getId();
	}
	
	private void saveOrderItems(List<OrderSaveItemReqDto> orderItems, Long orderId) {
		orderItems.forEach(reqItem -> {
			
			Order savedOrder = orderRepository.findById(orderId).orElseThrow();
			
			Item item = itemRepository.findById(reqItem.getItemId()).orElseThrow();
			
			int price = item.getPrice();
			int count = reqItem.getCount();
					
			OrderItem orderItem = OrderItem.builder()
					.count(count)
					.orderPrice(price)
					.item(item)
					.order(savedOrder)
					.build();			
						
			orderItemRepository.save(orderItem);
			
			// 犁绊贸府
			itemService.setStockQuantity(reqItem.getItemId(), -count);
			
		});	
	}
	
	
	public List<OrderResDto> getOrders() {
		
		// List<Order> orders = orderRepository.findAll();
		List<Order> orders = orderRepository.findAllOderFetchJoin1();
		
		List<OrderResDto> resOrders = orders.stream()
				.map(order -> new OrderResDto(order))
				.collect(Collectors.toList()); 
		
		return resOrders;
		
	}
	
	public List<OrderResDto> getOrdersDto() {
		
		return orderRepository.findAllOrderResDto();
		
	}
	
	public List<OrderResDto> getOrders3(Pageable pageable) {
		
		// List<Order> orders = orderRepository.findAll();
		List<Order> orders = orderRepository.findAllOderFetchJoin3(pageable);
		
		List<OrderResDto> resOrders = orders.stream()
				.map(order -> new OrderResDto(order))
				.collect(Collectors.toList()); 
		
		return resOrders;
		
	}
	
}
