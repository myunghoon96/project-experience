package com.springboot_jpa_mall.controller;

import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.dto.MemberDto;
import com.springboot_jpa_mall.dto.OrderItemDto;
import com.springboot_jpa_mall.entity.Cart;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.repository.CartRepository;
import com.springboot_jpa_mall.repository.MemberRepository;
import com.springboot_jpa_mall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
@Slf4j
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CartRepository cartRepository;


    @PostMapping("/order")
    public String order(Principal principal, Long itemId, Integer count) {
        log.info("OrderController itemId={}", itemId);
        log.info("OrderController principal={}", principal.getName());
        log.info("OrderController count={}", count);

        orderService.order(itemId, principal, count);
        return "redirect:/";

    }

    @PostMapping("/orders")
    public String orders(Principal principal) {
        Member member = memberRepository.findByMemberLoginId(principal.getName()).get();
        Cart cart = cartRepository.findByMember(member).get();


        orderService.orders(member, cart);
        return "redirect:/";

    }


}
