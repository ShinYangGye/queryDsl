package com.meta.repository.query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.meta.dto.req.MemberSearchConditionDto;
import com.meta.dto.res.MemberTeamResDto;
import com.meta.dto.res.QMemberTeamResDto;
import com.meta.entity.Member;
import static com.meta.entity.QTeam.team;

import static com.meta.entity.QMember.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class QueryDynamicTestReposotory {

	private final JPAQueryFactory query;
	
	public List<MemberTeamResDto> getMembersByBuilder(MemberSearchConditionDto condition) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(condition.getUserName())) {
			builder.and(member.name.eq(condition.getUserName()));
		}
		if (StringUtils.hasText(condition.getTeamName())) {
			builder.and(team.name.eq(condition.getTeamName()));
		}
		
				
		List<MemberTeamResDto> result = query
				.select(new QMemberTeamResDto(member, team))
				.from(member)
				.leftJoin(member.team, team)
				.where(builder)
				.fetch();
		
		return result;
	}
	
	public List<MemberTeamResDto> getMembersByBuilder2(MemberSearchConditionDto condition) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(condition.getUserName())) {
			builder.and(member.name.eq(condition.getUserName()));
		}
		if (StringUtils.hasText(condition.getTeamName())) {
			builder.and(team.name.eq(condition.getTeamName()));
		}
		
				
		List<MemberTeamResDto> result = query
				.select(new QMemberTeamResDto(member.name, team.name))
				.from(member)
				.leftJoin(member.team, team)
				.where(builder)
				.fetch();
		
		return result;
	}
	
}
