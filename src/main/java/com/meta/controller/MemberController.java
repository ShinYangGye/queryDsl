package com.meta.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.meta.dto.req.MemberSaveReqDto;
import com.meta.dto.req.MemberUpdReqDto;
import com.meta.dto.req.SearchCondition;
import com.meta.dto.res.MemberResDto;
import com.meta.entity.Member;
import com.meta.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping("/member")
	public Long saveMember(@RequestBody MemberSaveReqDto reqData) {
		return memberService.saveMember(reqData);
	}
	
	@GetMapping("/members")
	public List<MemberResDto> getMembers() {
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{memberId}")
	public MemberResDto getMember(@PathVariable Long memberId) {
		return memberService.getMember(memberId);
	}
	
	@GetMapping("/members-name")
	public List<MemberResDto> getMembersByNameContaining(SearchCondition con) {
		return memberService.getMembersByNameContaining(con.getMemberName());
	}
	
	@PutMapping("/member/{memberId}")
	public Long updateMember(@PathVariable Long memberId, @RequestBody MemberUpdReqDto reqData) {
		return memberService.updateMember(memberId, reqData);
	}
	
	@DeleteMapping("/member/{memberId}")
	public Long deleteMember(@PathVariable Long memberId) {
		return memberService.deleteMember(memberId);
	}
}
