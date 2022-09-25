package com.meta.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meta.dto.req.MemberSearchConditionDto;
import com.meta.dto.res.MemberTeamResDto;
import com.meta.repository.MemberRepository;
import com.meta.repository.query.QueryDynamicTestReposotory;
import com.meta.repository.query.QueryMemberTestRepository;
import com.meta.repository.query.QueryProjectionTestRepository;
import com.meta.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dsl")
public class DslTestController {

	private final QueryMemberTestRepository queryMemberTestRepository;
	private final QueryDynamicTestReposotory queryDynamicTestReposotory;
	
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	
	private final QueryProjectionTestRepository queryProjectionTestRepository;
	
	@GetMapping("/query1")
	public void startDsl() {		
		queryMemberTestRepository.startQueryDsl();		
	}
	
	@GetMapping("/query2")
	public void query1() {
		queryProjectionTestRepository.query1();
	}
	
	@GetMapping("/query3")
	public List<MemberTeamResDto> query3(MemberSearchConditionDto condition) {
		return memberService.getMemberTeam(condition);
	}
	
	@GetMapping("/query4")
	public Page<MemberTeamResDto> query4(MemberSearchConditionDto condition, Pageable pageable) {
		return memberService.getMemberTeamPaging(condition, pageable);
	}
	
}
