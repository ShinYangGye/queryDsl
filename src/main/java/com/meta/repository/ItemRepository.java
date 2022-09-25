package com.meta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meta.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
