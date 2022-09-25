package com.meta.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the db_order database table.
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="db_order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=1)
	private String status;
	
	@Column(name="order_at")
	private Timestamp orderAt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id", nullable=false)
	private Member member;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="delivery_id", nullable=false)
	private Delivery delivery;

	@OneToMany(mappedBy="order")
	private List<OrderItem> orderItems = new ArrayList<>();;

}