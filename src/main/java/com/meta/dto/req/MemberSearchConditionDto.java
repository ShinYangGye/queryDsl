package com.meta.dto.req;

import lombok.Data;

@Data
public class MemberSearchConditionDto {

	private String userName;
	private String teamName;
	private Integer ageGoe;
	private Integer ageLoe;
	
}
