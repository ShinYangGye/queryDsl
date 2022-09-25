package com.meta.dto.res;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DsMemberResDto {

	private String username;
	private int age;
	
	@QueryProjection
	public DsMemberResDto(String name, int age) {
		this.username = name;
		this.age = age;
	}
	
}
