package com.meta.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the db_member_dtl database table.
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="db_member_dtl")
@NamedQuery(name="MemberDtl.findAll", query="SELECT m FROM MemberDtl m")
public class MemberDtl  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=50)
	private String comment;

	@Column(name="member_id", nullable=false)
	private Long memberId;

}