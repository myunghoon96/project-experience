package com.my_shop.controller;

import com.my_shop.dto.MemberFormDto;
import com.my_shop.entity.Member;
import com.my_shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("로그인 성공")
    public void loginSuccessTest() throws Exception{
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("member1");
        memberFormDto.setEmail("member1@naver.com");
        memberFormDto.setPassword("1234");
        memberFormDto.setAddress("address1");

        Member createdMember = Member.createMember(memberFormDto, passwordEncoder);
        Member savedMember = memberService.saveMember(createdMember);

        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login")
                .user("member1@naver.com").password("1234"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패")
    public void loginFailTest() throws Exception{
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("member1");
        memberFormDto.setEmail("member1@naver.com");
        memberFormDto.setPassword("1234");
        memberFormDto.setAddress("address1");

        Member createdMember = Member.createMember(memberFormDto, passwordEncoder);
        Member savedMember = memberService.saveMember(createdMember);

        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/members/login")
                        .user("member1@naver.com").password("12345678"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}