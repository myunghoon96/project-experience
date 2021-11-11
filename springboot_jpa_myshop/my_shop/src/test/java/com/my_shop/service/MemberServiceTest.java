package com.my_shop.service;

import com.my_shop.dto.MemberFormDto;
import com.my_shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입")
    public void createSaveMemberTest(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("member1");
        memberFormDto.setEmail("member1@naver.com");
        memberFormDto.setPassword("1234");
        memberFormDto.setAddress("address1");

        Member createdMember = Member.createMember(memberFormDto, passwordEncoder);
        Member savedMember = memberService.saveMember(createdMember);

        assertEquals(createdMember.getId(), savedMember.getId());
        assertEquals(createdMember.getEmail(), savedMember.getEmail());
    }

}