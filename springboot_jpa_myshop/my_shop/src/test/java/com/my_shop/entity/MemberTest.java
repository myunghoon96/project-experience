package com.my_shop.entity;

import com.my_shop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("BaseEntity Auditing 테스트")
    @WithMockUser(username = "userA", roles = "USER")
    public void baseEntityTest() {
        Member newMember = new Member();
        memberRepository.save(newMember);

        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(newMember.getId())
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("getCreatedBy = " + findMember.getCreatedBy());
        System.out.println("getModifiedBy = " + findMember.getModifiedBy());
        System.out.println("getRegTime = " + findMember.getRegTime());
        System.out.println("getUpdateTime = " + findMember.getUpdateTime());
    }

}