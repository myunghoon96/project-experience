package com.my_shop.repository;

import com.my_shop.dto.MemberFormDto;
import com.my_shop.entity.Cart;
import com.my_shop.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PersistenceContext
    EntityManager em;

    public Member createSampleMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("member1");
        memberFormDto.setEmail("member1@naver.com");
        memberFormDto.setAddress("address1");
        memberFormDto.setPassword("1234");

        Member createdMember = Member.createMember(memberFormDto, passwordEncoder);
        return createdMember;
    }

    @DisplayName("장바구니 회원ID 조회")
    @Test
    public void findCartAndMemberTest(){
        Member member = this.createSampleMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);

        Assertions.assertEquals(cart.getMember().getId(), member.getId());
    }

}