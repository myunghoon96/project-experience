package com.springboot_jpa_mall.repository;

import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);
    Optional<List<Order>> findByMember(Member member);

    @Query("SELECT distinct o FROM Order o join fetch o.member m join fetch o.orderItems oi join fetch oi.item i")
    public List<Order> findAll2();

    @Query("SELECT distinct o FROM Order o join fetch o.member m join fetch o.orderItems oi join fetch oi.item i where m.memberLoginId = :memberLoginId")
    public List<Order> findByMemberLoginId2(@Param("memberLoginId") String memberLoginId);
}
