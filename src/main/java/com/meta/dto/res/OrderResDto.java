package com.meta.dto.res;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.meta.entity.Order;

import lombok.Data;

@Data
public class OrderResDto {

	private Long orderId;
	private String status;
	private Timestamp orderAt;	
	
	private Member member;
	private List<Item> items;	
	
	public OrderResDto(Order order) {
		this.orderId = order.getId();
		this.status = order.getStatus();
		this.orderAt = order.getOrderAt();
				
		this.member = new Member(order.getMember().getId(), order.getMember().getEmail(), order.getMember().getName());
		
		this.items = order.getOrderItems().stream()
				.map(item -> new Item(item.getItem().getId(), item.getItem().getName(), item.getItem().getPrice(), item.getCount()))
				.collect(Collectors.toList());
	}
	
}

@Data
class Member {
	private Long memberId;
	private String email;
	private String name;
	
	public Member(Long memberId, String email, String name) {
		this.memberId = memberId;
		this.email = email;
		this.name = name;
	}				
	
}

@Data
class Item {
	private Long itemId;
	private String name;
	private int price;
	private int count;
	
	public Item(Long itemId, String name, int price, int count) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.count = count;
	}
			
}