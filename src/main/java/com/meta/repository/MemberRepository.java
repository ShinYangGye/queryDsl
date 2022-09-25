package com.meta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meta.entity.Member;
import com.meta.repository.query.member.QueryMemberRepository;


public interface MemberRepository extends JpaRepository<Member, Long>, QueryMemberRepository {
	List<Member> findByNameContaining(String name);
}
