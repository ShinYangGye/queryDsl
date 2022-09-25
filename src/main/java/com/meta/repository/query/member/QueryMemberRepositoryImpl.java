package com.meta.repository.query.member;

import static com.meta.entity.QMember.member;
import static com.meta.entity.QTeam.team;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.meta.dto.req.MemberSearchConditionDto;
import com.meta.dto.res.MemberTeamResDto;
import com.meta.dto.res.QMemberTeamResDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueryMemberRepositoryImpl implements QueryMemberRepository {
	
	private final JPAQueryFactory query;

	@Override
	public List<MemberTeamResDto> getMemberTeam(MemberSearchConditionDto condition) {

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

	@Override
	public Page<MemberTeamResDto> getMemberTeamPaging(MemberSearchConditionDto condition, Pageable pageable) {		
		
		List<MemberTeamResDto> result = query
				.select(new QMemberTeamResDto(member, team))
				.from(member)
				.leftJoin(member.team, team)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		Long totalCount = query
				.select(member.count())
				.from(member)
				.fetchOne();
		
		return new PageImpl<>(result, pageable, totalCount);
	}

	@Override
	public Page<MemberTeamResDto> getMemberTeamPaging2(MemberSearchConditionDto condition, Pageable pageable) {
		List<MemberTeamResDto> result = query
				.select(new QMemberTeamResDto(member, team))
				.from(member)
				.leftJoin(member.team, team)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> totalCount = query
				.select(member.count())
				.from(member);
		
		return PageableExecutionUtils.getPage(result, pageable, () -> totalCount.fetchOne());
	}

}
