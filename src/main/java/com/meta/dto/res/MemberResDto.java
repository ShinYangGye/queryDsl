package com.meta.dto.res;

import java.sql.Timestamp;
import com.meta.entity.Member;
import lombok.Data;

@Data
public class MemberResDto {

	private Long memberId;
	private String email;
	private String name;
	private String city;
	private String street;
	private String zipcode;
	private Timestamp regAt;
	
	public MemberResDto(Member member) {
		this.memberId = member.getId();
		this.email = member.getEmail();
		this.name = member.getName();
		this.city = member.getCity();
		this.street = member.getStreet();
		this.zipcode = member.getZipcode();
		this.regAt = member.getRegAt();
	}
		
}
