package com.meta.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * The persistent class for the db_delivery database table.
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="db_delivery")
@NamedQuery(name="Delivery.findAll", query="SELECT d FROM Delivery d")
public class Delivery  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(nullable=false, length=1)
	private String status;

	@Column(nullable=false, length=50)
	private String city;

	@Column(nullable=false, length=50)
	private String street;

	@Column(nullable=false, length=50)
	private String zipcode;

	@OneToOne(mappedBy="delivery")
	private Order order;

}