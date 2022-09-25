package com.meta.dto.req;

import java.util.List;

import lombok.Data;

@Data
public class OrderSaveReqDto {

	private Long memberId;
	private List<OrderSaveItemReqDto> orderItems;	
	
}
