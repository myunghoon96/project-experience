package com.springboot_jpa_mall.repository;

import com.springboot_jpa_mall.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItemName(String itemName);
    Optional<Item> findById(Long id);
    Page<Item> findAll(Pageable pageable);
}
