package com.meta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.dto.req.MemberSaveReqDto;
import com.meta.dto.req.MemberSearchConditionDto;
import com.meta.dto.req.MemberUpdReqDto;
import com.meta.dto.res.MemberResDto;
import com.meta.dto.res.MemberTeamResDto;
import com.meta.entity.Member;
import com.meta.repository.MemberRepository;
import com.meta.repository.query.QueryDynamicTestReposotory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	
	private final MemberRepository memberRepository;	


	@Transactional
	public Long saveMember(MemberSaveReqDto reqData) {
		
		Member member = Member.builder()
				.email(reqData.getEmail())
				.name(reqData.getName())
				.city(reqData.getCity())
				.street(reqData.getStreet())
				.zipcode(reqData.getZipcode())
				.build();
		
		memberRepository.save(member);
		
		return member.getId();
	}
	
	public List<MemberResDto> getMembers() {		
		List<Member> members = memberRepository.findAll();
		
		List<MemberResDto> resMembers = members.stream()
				.map(member -> new MemberResDto(member))
				.collect(Collectors.toList());				
		return resMembers;
	}
	
	public MemberResDto getMember(Long memberId) {		
		Member member = memberRepository.findById(memberId).orElseThrow();		
		
		MemberResDto resMember = new MemberResDto(member);							
		return resMember;		
	}
	
	public List<MemberResDto> getMembersByNameContaining(String name) {		
		List<Member> members = memberRepository.findByNameContaining(name);
		
		List<MemberResDto> resMembers = members.stream()
				.map(member -> new MemberResDto(member))
				.collect(Collectors.toList());				
		return resMembers;
	}
	
	@Transactional
	public Long updateMember(Long memberId, MemberUpdReqDto reqData) {
		
		Member member = memberRepository.findById(memberId).orElseThrow();
		
		member.setName(reqData.getName());
		member.setCity(reqData.getCity());
		member.setStreet(reqData.getStreet());
		member.setZipcode(reqData.getZipcode());
		
		return member.getId();
	}
	
	@Transactional
	public Long deleteMember(Long memberId) {
		memberRepository.deleteById(memberId);		
		return memberId;
	}
	
	
	public List<MemberTeamResDto> getMemberTeam(MemberSearchConditionDto condition) {		
		return memberRepository.getMemberTeam(condition);		
	}
	
	public Page<MemberTeamResDto> getMemberTeamPaging(MemberSearchConditionDto condition, Pageable pageable) {		
		return memberRepository.getMemberTeamPaging(condition, pageable);		
	}
}
