package com.meta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.dto.req.ItemSaveReqDto;
import com.meta.dto.res.ItemResDto;
import com.meta.entity.Item;
import com.meta.repository.ItemRepository;
import com.meta.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	public Long saveItem(ItemSaveReqDto reqData) {
		
		Item item = Item.builder()
				.name(reqData.getName())
				.price(reqData.getPrice())
				.stockQuantity(reqData.getStockQuantity())
				.dtype(reqData.getDtype())
				.artist(reqData.getArtist())
				.etc(reqData.getEtc())
				.author(reqData.getAuthor())
				.isbn(reqData.getIsbn())
				.director(reqData.getDirector())
				.actor(reqData.getActor())
				.build();
		
		itemRepository.save(item);
		
		return item.getId();
		
	}
	
	public List<ItemResDto> getItems() {
		
		List<Item> items = itemRepository.findAll();
		
		List<ItemResDto> resItems = items.stream()
				.map(item -> new ItemResDto(item))
				.collect(Collectors.toList());
		
		return resItems;
	}
	
	public ItemResDto getItem(Long itemId) {
		Item item = itemRepository.findById(itemId).orElseThrow();
		ItemResDto resItem = new ItemResDto(item);
		
		return resItem;
		
	}
	
	@Transactional
	public void setStockQuantity(Long itemId, int orderCount) {
		
		Item item = itemRepository.findById(itemId).orElseThrow();
		
		int resQuantity = item.getStockQuantity() + orderCount;
		
		if (resQuantity < 0) {
			throw new IllegalArgumentException("재고수량이  부족합니다.");
		}
		
		item.setStockQuantity(resQuantity);
		
	}

}
