package com.springboot_jpa_mall.repository;

import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
}
