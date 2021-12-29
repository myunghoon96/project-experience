package com.springboot_jpa_mall.service;

import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.entity.Cart;
import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.entity.OrderItem;
import com.springboot_jpa_mall.repository.CartRepository;
import com.springboot_jpa_mall.repository.ItemRepository;
import com.springboot_jpa_mall.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;

    public void addCartItem(Principal principal, Long itemId, Integer count) {
        log.info("addCartItem");
        log.info("{}", principal.getName());
        log.info("{}", count);

        Member member = memberRepository.findByMemberLoginId(principal.getName()).get();

        log.info("member {}", member.getMemberLoginId());
        Cart cart = new Cart();
        cart.setMember(member);
        List<OrderItem> orderItems = new ArrayList<>();
        cart.setOrderItems(orderItems);
        //        cart = cartRepository.findByMember(member).get();
//        if (cart == null) {
//            cart = new Cart();
//            cart.setMember(member);
//        }

        log.info("cart {}", cart.getId());

        Item item = itemRepository.findById(itemId).get();
        OrderItem orderItem = OrderItem.builder()
                                .item(item)
                                .count(count)
                                .orderPrice(item.getItemPrice())
                                .amount(count*item.getItemPrice())
                                .build();
        cart.getOrderItems().add(orderItem);


        cartRepository.save(cart);
    }

}
