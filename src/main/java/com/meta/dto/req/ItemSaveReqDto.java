package com.meta.dto.req;

import lombok.Data;

@Data
public class ItemSaveReqDto {

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
}
