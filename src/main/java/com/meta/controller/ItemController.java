package com.meta.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meta.dto.req.ItemSaveReqDto;
import com.meta.dto.res.ItemResDto;
import com.meta.entity.Item;
import com.meta.repository.ItemRepository;
import com.meta.service.ItemService;
import com.meta.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {
	
	private final ItemService itemService;
	
	@PostMapping("/item")
	public Long saveItem(@RequestBody ItemSaveReqDto reqData) {		
		return itemService.saveItem(reqData);		
	}
	
	@GetMapping("/items")
	public List<ItemResDto> getItems() {
		return itemService.getItems();
	}
	
	@GetMapping("/item/{itemId}")
	public ItemResDto getItem(@PathVariable Long itemId) {
		return itemService.getItem(itemId);
	}
}
