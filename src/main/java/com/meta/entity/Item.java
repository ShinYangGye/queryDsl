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

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the db_item database table.
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="db_item")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(nullable=false, length=50)
	private String name;
	
	@Column(nullable=false)
	private int price;
	
	@Column(name="stock_quantity", nullable=false)
	private int stockQuantity;
	
	@Column(length=50)
	private String dtype;

	@Column(length=50)
	private String artist;
	
	@Column(length=50)
	private String etc;

	@Column(length=50)
	private String author;
	
	@Column(length=50)
	private String isbn;

	@Column(length=50)
	private String director;
	
	@Column(length=50)
	private String actor;

	@OneToMany(mappedBy="item")
	private List<OrderItem> orderItems = new ArrayList<>();	
	
}