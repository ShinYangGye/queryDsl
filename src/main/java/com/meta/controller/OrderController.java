package com.meta.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meta.dto.req.OrderSaveReqDto;
import com.meta.dto.res.OrderResDto;
import com.meta.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/order")
	public Long saveOrder(@RequestBody OrderSaveReqDto reqData) {		
		return orderService.saveOrder(reqData);		
	}
	
	@GetMapping("/orders")
	public List<OrderResDto> getOrders() {
		return orderService.getOrders();
	}
	
	@GetMapping("/orders2")
	public List<OrderResDto> getOrdersDto() {
		return orderService.getOrdersDto();
	}
	
	@GetMapping("/orders3")
	public List<OrderResDto> getOrders3(Pageable pageable) {
		return orderService.getOrders3(pageable);
	}
}
