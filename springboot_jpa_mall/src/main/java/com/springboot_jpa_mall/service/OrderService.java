package com.springboot_jpa_mall.service;

import com.springboot_jpa_mall.constant.OrderStatus;
import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.dto.OrderItemDto;
import com.springboot_jpa_mall.entity.*;
import com.springboot_jpa_mall.repository.ItemRepository;
import com.springboot_jpa_mall.repository.MemberRepository;
import com.springboot_jpa_mall.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    CartService cartService;

    @Transactional
    public void order(Long itemId, Principal principal, Integer count) {

        String memberLoginId = principal.getName();
        Optional<Member> memberWrapper = memberRepository.findByMemberLoginId(memberLoginId);
        Member member = memberWrapper.get();
        log.info("member={}", member.toString());

        Optional<Item> itemWrapper = itemRepository.findById(itemId);
        Item item = itemWrapper.get();
        log.info("item={}", item.toString());

        OrderItem orderItem = item.createOrderItem(item, item.getItemPrice(), count);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        log.info("orderItems={}", orderItems.toString());

        Order order = new Order();
        order.setMember(member);
        order.setOrderItems(orderItems);

        orderItem.setOrder(order);
        order.setOrderStatus(OrderStatus.OK);
        order.setTotalAmount(order.getTotalAmount());

        item.subtractItemStock(count);

        orderRepository.save(order);
        log.info("order={}", order.toString());
    }

    @Transactional
    public void orders(Member member, Cart cart) {

        List<OrderItem> orderItems = cartService.getCartItems(member);
        log.info("1");
        Order order = new Order();
        order.setMember(member);
        log.info("2");

        for (OrderItem orderItem : orderItems) {
            order.getOrderItems().add(orderItem);
            orderItem.setOrder(order);
            Item item = orderItem.getItem();
            item.subtractItemStock(orderItem.getCount());
        }

        log.info("3");
        order.setOrderStatus(OrderStatus.OK);
        order.setTotalAmount(order.getTotalAmount());
        log.info("4");
        orderRepository.save(order);
    }
}
