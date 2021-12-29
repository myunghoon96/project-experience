package com.springboot_jpa_mall.controller;

import com.springboot_jpa_mall.dto.MemberDto;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/login")
    public String memberLogin() {
        return "member/loginForm";
    }

    @GetMapping("/signup")
    public String memberSignUp(Model model) {
        model.addAttribute("memberDto", new MemberDto("",""));
        return "member/signupForm";
    }

    @PostMapping("/signup")
    public String memberSignUp(@Valid MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "member/signupForm";
        }

        memberService.addMember(memberDto);

        return "redirect:/";
    }

    @PostConstruct
    public void initializing() {

        MemberDto memberDto = MemberDto.builder()
                .memberLoginId("userA")
                .password("1234")
                .build();
        memberService.addMember(memberDto);
    }

}
