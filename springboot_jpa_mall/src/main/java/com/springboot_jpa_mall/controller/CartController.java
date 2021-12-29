package com.springboot_jpa_mall.controller;

import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.repository.CartRepository;
import com.springboot_jpa_mall.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
@Slf4j
@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;

    @PostMapping("/cart")
    public String addCartItem(Principal principal, Long itemId, Integer count) {
        log.info("cart controller");
        log.info("{}", principal.getName());
        log.info("{}", count);
        cartService.addCartItem(principal, itemId, count);

        return "redirect:/";
    }

    @GetMapping("/cart")
    public void getCartItem() {

    }

}
