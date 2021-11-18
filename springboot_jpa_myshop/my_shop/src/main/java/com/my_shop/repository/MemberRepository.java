package com.my_shop.repository;

import com.my_shop.entity.Item;
import com.my_shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
