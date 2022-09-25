package com.meta.dto.req;

import lombok.Data;

@Data
public class MemberUpdReqDto {
	private String name;
	private String city;
	private String street;
	private String zipcode;
}
