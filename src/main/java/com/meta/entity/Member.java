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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the db_member database table.
 * 
 */
@Data
@ToString(exclude = {"orders", "team"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="db_member")
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(nullable=false, length=50)
	private String email;

	@Column(nullable=false, length=50)
	private String name;	
	
	@Column
	private int age;

	@Column(length=50)
	private String city;

	@Column(length=50)
	private String street;

	@Column(length=50)
	private String zipcode;

	@Column(name="reg_at")
	private Timestamp regAt;

	@Column(name="upd_at")
	private Timestamp updAt;

	@OneToMany(mappedBy="member")
	private List<Order> orders = new ArrayList<>();;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

}