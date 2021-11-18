package com.my_shop.service;

import com.my_shop.constant.ItemStatus;
import com.my_shop.dto.OrderDto;
import com.my_shop.entity.Item;
import com.my_shop.entity.Member;
import com.my_shop.entity.Order;
import com.my_shop.entity.OrderItem;
import com.my_shop.repository.ItemRepository;
import com.my_shop.repository.MemberRepository;
import com.my_shop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;

    public Item saveSampleItem(){
        Item item = new Item();
        item.setItemName("item1");
        item.setPrice(100);
        item.setDetail("item1 detail");
        item.setItemStatus(ItemStatus.SELL);
        item.setStock(100);
        return itemRepository.save(item);
    }

    public Member saveSampleMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);

    }

    @Test
    @DisplayName("주문 테스트")
    public void order(){
        Item item = saveSampleItem();
        Member member = saveSampleMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());

        Long orderId = orderService.order(orderDto, member.getEmail());
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount()*item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }
}