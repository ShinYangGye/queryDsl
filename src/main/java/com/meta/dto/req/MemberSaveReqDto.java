package com.meta.dto.req;

import lombok.Data;

@Data
public class MemberSaveReqDto {

	private String email;
	private String name;
	private String city;
	private String street;
	private String zipcode;
}
