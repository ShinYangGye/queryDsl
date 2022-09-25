package com.meta.repository.query.member;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.meta.dto.req.MemberSearchConditionDto;
import com.meta.dto.res.MemberTeamResDto;

public interface QueryMemberRepository {

	List<MemberTeamResDto> getMemberTeam(MemberSearchConditionDto condition);
	
	Page<MemberTeamResDto> getMemberTeamPaging(MemberSearchConditionDto condition, Pageable pageable);
	
	Page<MemberTeamResDto> getMemberTeamPaging2(MemberSearchConditionDto condition, Pageable pageable);
	
}
