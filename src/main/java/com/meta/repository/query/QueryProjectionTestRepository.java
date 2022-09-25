package com.meta.repository.query;

import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.meta.entity.QMember.member;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.meta.dto.res.DsMemberResDto;
import com.meta.dto.res.MemberResDto;
import com.meta.dto.res.QDsMemberResDto;
import com.meta.entity.Member;
import com.meta.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QueryProjectionTestRepository {
	
	private final EntityManager em;
	
	private final MemberRepository memberRepository;

	private final JPAQueryFactory query;	
	
	private void queryProjectionDto() {
		
		List<DsMemberResDto> result = query
				.select(new QDsMemberResDto(member.name, member.age))
				.from(member)
				.fetch();		
		
		for (DsMemberResDto t : result) {
			log.info("result ================= {}", t);
		}
	}
	
	public void dynamicQueryByBooleanBuilder() {
		
		String paramName = null;
		Integer paramAge = 10;
		
		List<DsMemberResDto> result = searchMember1(paramName, paramAge);
		
		for (DsMemberResDto t : result) {
			log.info("result ================= {}", t);
		}
		
	}
	
	private List<DsMemberResDto> searchMember1(String paramName, Integer paramAge) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if (paramName != null) {
			builder.and(member.name.eq(paramName));
		}
		if (paramAge != null) {
			builder.and(member.age.eq(paramAge));
		}
		
		List<DsMemberResDto> result = query
				.select(new QDsMemberResDto(member.name, member.age))
				.from(member)
				.where(builder)
				.fetch();
		
		return result;
		
	}
	
	public void dynamicQueryByWhereParam() {
		
		String paramName = "홍길동1";
		Integer paramAge = 20;
		
		List<DsMemberResDto> result = searchMember2(paramName, paramAge);
		
		for (DsMemberResDto t : result) {
			log.info("result ================= {}", t);
		}
		
	}
	
	private List<DsMemberResDto> searchMember2(String paramName, Integer paramAge) {
			
		List<DsMemberResDto> result = query
				.select(new QDsMemberResDto(member.name, member.age))
				.from(member)
				.where(
						allEq(paramName, paramAge)
					//nameEq(paramName),
					//ageEq(paramAge)
				)
				.fetch();
		
		return result;
		
	}
	
	// private Predicate nameEq(String paramName) {
	private BooleanExpression nameEq(String paramName) {
		return paramName == null ? null : member.name.eq(paramName);
	}
	
	// private Predicate ageEq(Integer paramAge) {
	private BooleanExpression ageEq(Integer paramAge) {
		return paramAge == null ? null : member.age.eq(paramAge);
	}
	
	private BooleanExpression allEq(String paramName, Integer paramAge) {
		return nameEq(paramName).and(ageEq(paramAge));
	}
	

	
	
	private void bulkUpdate() {
		
		Long memberId = 1L;
		
		Optional<Member> member1 = memberRepository.findById(memberId);
		
		log.info("member1 ============== {}", member1);
		
		long count = query
				.update(member)
				.set(member.name, "홍길동1")
				.set(member.age, member.age.add(5))
				.where(
					member.id.eq(memberId)
				)
				.execute();
				
		em.flush();
		em.clear();
		
		log.info("count ============== {}", count);
		
		Optional<Member> member2 = memberRepository.findById(memberId);
		log.info("member2 ============== {}", member2);
		
	}
	
	private void bulkDelete() {
		
		Long memberId = 7L;
		
		
		long count = query
				.delete(member)
				.where(
					member.id.eq(memberId)
				)
				.execute();
		
		log.info("count ============== {}", count);
	}
	
	private void sqlFunction() {
		
		// MySQLDialect xx = null;
		//MySQLDialect 에 등록된 function 만 사용 가능하다.
		
		List<String> result = query
				.select(
					Expressions.stringTemplate(
							"function('concat', {0}, {1})", 
							member.name, "member")
				)
				.from(member)
				.fetch();
		
		for (String t : result) {
			log.info("result ================= {}", t);
		}
		
	}
	
	private void sqlFunction2() {
		
		List<Tuple> result = query
				.select(member.id, member.name)
				.from(member)
				.where(member.name.lower().eq("aaaa"))
				.fetch();
		
		for (Tuple t : result) {
			log.info("result ================= {}", t);
		}
	}
	
	
	// @Transactional
	public void query1() {		
		sqlFunction2();		
	}
	
}
