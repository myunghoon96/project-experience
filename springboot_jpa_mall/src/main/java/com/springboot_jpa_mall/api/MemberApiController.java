package com.springboot_jpa_mall.api;

import com.springboot_jpa_mall.dto.MemberDto;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.repository.MemberRepository;
import com.springboot_jpa_mall.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/member/signup")
    public memberSignUpResponse memberSignUp(@RequestBody MemberDto memberDto) {

        Long newMemberId = memberService.addMember(memberDto);
        String newMemberLoginId = memberRepository.findById(newMemberId).get().getMemberLoginId();
        return new memberSignUpResponse(newMemberId, newMemberLoginId);
    }
    @Data
    @AllArgsConstructor
    static class memberSignUpResponse {
        Long memberId;
        String memberLoginId;
    }




}
