package com.meta.dto.res;

import java.sql.Timestamp;

import com.meta.entity.Item;
import com.meta.entity.Member;
import lombok.Data;

@Data
public class ItemResDto {

	private Long itemId;
	private String name;
	private int price;
	private int stockQuantity;
	private String dtype;
	
	private String artist;
	private String etc;
	private String author;
	private String isbn;
	private String director;
	private String actor;
	
	public ItemResDto(Item item) {
		super();
		this.itemId = item.getId();
		this.name = item.getName();
		this.price = item.getPrice();
		this.stockQuantity = item.getStockQuantity();
		this.dtype = item.getDtype();
		this.artist = item.getArtist();
		this.etc = item.getEtc();
		this.author = item.getAuthor();
		this.isbn = item.getIsbn();
		this.director = item.getDirector();
		this.actor = item.getActor();
	}
			
}
