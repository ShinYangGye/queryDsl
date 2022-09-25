package com.meta.repository.query;

import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.meta.dto.res.DsMemberResDto;
import com.meta.entity.Member;
import com.meta.entity.QMember;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.meta.entity.QMember.member;
import static com.meta.entity.QTeam.team;
import static com.meta.entity.QMemberDtl.memberDtl;

@Slf4j
@RequiredArgsConstructor
@Repository
public class QueryMemberTestRepository {

	private final JPAQueryFactory query;
	

	
	public void query2() {
		
		List<Member> result = query
				.selectFrom(member)
				.where(
					member.name.contains("홍길동"),
					member.city.startsWith("서울시2")
				)
				.fetch();
		for (Member m : result) {
			log.info("query 2 ====================== {}", m);
		}
	}
	
	public void querySort() {
		
		List<Member> result = query
				.selectFrom(member)
				.where(
					member.age.goe(10)
				)
				.orderBy(
					member.age.desc(),	
					member.zipcode.desc().nullsLast()
				)
				.fetch();
		
		for (Member m : result) {
			log.info("====================== {}", m);
		}
		
	}
	
	public void aggregation() {
		
		List<Tuple> result = query
				.select(
					member.count(),
					member.age.sum(),
					member.age.avg()
				)
				.from(member)
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t.get(member.count()));
			log.info("====================== {}", t.get(member.age.sum()));
		}
		
	}
	
	public void group() {
		
		List<Tuple> result = query
				.select(team.name, member.age.avg())
				.from(member)
				.join(member.team, team)
				.groupBy(team.name)
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
	}
	
	public void join() {
		
		List<Tuple> result = query
				.select(member, team)
				.from(member)
				.join(member.team, team)
				.where(
					team.name.eq("team1")	
				).fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}		
		
	}
	
	public void thetaJoin() {
		
		List<Tuple> result = query
				.select(member, memberDtl)
				.from(member, memberDtl)
				.where(member.id.eq(memberDtl.memberId))
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}	
		
	}
	
	public void joinOnFilter() {
		
		List<Tuple> result = query
				.select(member, team)
				.from(member)
				.leftJoin(member.team, team).on(team.name.eq("team1"))
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
		
	}
	
	public void joinOnNoRelation() {
		
		List<Tuple> result = query
				.select(member, memberDtl)
				.from(member)
				.leftJoin(memberDtl).on(member.id.eq(memberDtl.memberId))
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
	}
	
	public void fetchJoin() {
		List<Tuple> result = query
				.select(member, team)
				.from(member)
				.join(member.team, team).fetchJoin()
				.where(member.name.eq("홍길동1"))
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
		
	}
	
	public void subQuery() {
		
		QMember memberSub = new QMember("memberSub");
		
		List<Member> result = query
				.selectFrom(member)
				.where(
					member.age.eq(
						JPAExpressions
							.select(memberSub.age.min())
							.from(memberSub)
					)
				).fetch();
		
		for (Member t : result) {
			log.info("====================== {}", t);
		}
	}
	
	public void subQuery2() {
		
		QMember memberSub = new QMember("memberSub");
		
		List<Member> result = query
				.selectFrom(member)
				.where(
					member.id.in(
						JPAExpressions
						.select(memberSub.id)
						.from(memberSub)
						.where(memberSub.id.goe(6))
					)
				).fetch();
		
		
		for (Member t : result) {
			log.info("====================== {}", t);
		}
	}
	
	
	public void subQuery3() {
		
		QMember memberSub = new QMember("memberSub");
		
		List<Tuple> result = query
				.select(
					member.name,
					JPAExpressions.select(memberSub.age.avg().round()).from(memberSub).where(memberSub.age.goe(30))
				)
				.from(member)
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
		
	}
	
	public void case1() {
		
		List<Tuple> result = query
				.select(
					member.name,
					member.age
					.when(20).then("이십")
					.when(40).then("사십")
					.otherwise("기타")
				)
				.from(member)
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
		
	}
	
	public void case2() {
		
		List<Tuple> result = query
				.select(
					member.name,
					new CaseBuilder()
						.when(member.age.between(0, 20)).then("0 ~ 20")
						.when(member.age.between(21, 40)).then("21 ~ 40")
						.otherwise("기타")
				)
				.from(member)
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
		
	}
	
	public void constant() {
		
		List<Tuple> result = query
				.select(member.name, Expressions.constant("A"))
				.from(member)
				.fetch();
		
		for (Tuple t : result) {
			log.info("====================== {}", t);
		}
	}
	
	public void concat() {
		
		List<String> result = query
				.select(member.name.concat("_").concat(member.age.stringValue()))
				.from(member)
				.fetch();
		
		
		for (String t : result) {
			log.info("====================== {}", t);
		}
	}
	
	public void findDtoBySetter() {
		
		List<DsMemberResDto> result = query
				.select(
					Projections.bean(DsMemberResDto.class, 
						member.name, 
						member.age
					)
				)
				.from(member)
				.fetch();
		
		for (DsMemberResDto t : result) {
			log.info("====================== {}", t);
		}
	}
	
	public void findDtoByFields() {
		
		List<DsMemberResDto> result = query
				.select(
					Projections.fields(DsMemberResDto.class, 
						member.name.as("name"), 
						member.age.as("age")
					)
				)
				.from(member)
				.fetch();
		
		for (DsMemberResDto t : result) {
			log.info("====================== {}", t);
		}
	}
	
	public void findDtoByConstructor() {
		
		List<DsMemberResDto> result = query
				.select(
					Projections.constructor(DsMemberResDto.class, 
						member.name, 
						member.age
					)
				)
				.from(member)
				.fetch();
		
		for (DsMemberResDto t : result) {
			log.info("====================== {}", t);
		}
	}
	
	/*
	 * 
	 * */
	public void startQueryDsl() {				
		findDtoByConstructor();		
	}
	
}
