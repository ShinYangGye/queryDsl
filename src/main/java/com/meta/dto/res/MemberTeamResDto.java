package com.meta.dto.res;

import com.meta.entity.Member;
import com.meta.entity.Team;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberTeamResDto {

	private Long memberId;
	private String memberName;
	private Integer memberAge;	
	private Long teamId;
	private String teamName;
	
	@QueryProjection
	public MemberTeamResDto(String memberName, String teamName) {
		this.memberName = memberName;
		this.teamName = teamName;
	}
	
	@QueryProjection
	public MemberTeamResDto(Member member, Team team) {
		this.memberId = member.getId();
		this.memberName = member.getName();
		this.memberAge = member.getAge();
		this.teamId = team.getId();
		this.teamName = team.getName();
	}
	
}
