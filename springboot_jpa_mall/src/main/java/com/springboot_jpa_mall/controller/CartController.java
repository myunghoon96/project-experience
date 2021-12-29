package com.springboot_jpa_mall.controller;

import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.entity.OrderItem;
import com.springboot_jpa_mall.repository.CartRepository;
import com.springboot_jpa_mall.repository.MemberRepository;
import com.springboot_jpa_mall.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/cart")
    public String addCartItem(Principal principal, Long itemId, Integer count) {
        log.info("cart controller");
        log.info("{}", principal.getName());
        log.info("{}", count);
        cartService.addCartItem(principal, itemId, count);

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartItems(Principal principal, Model model) {
        Member member = memberRepository.findByMemberLoginId(principal.getName()).get();
        List<OrderItem> orderItems = cartService.getCartItems(member);
        model.addAttribute("orderItems", orderItems);
        return "cart/cartItems";
    }

}
