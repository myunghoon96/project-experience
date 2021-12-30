package com.springboot_jpa_mall.api;

import com.springboot_jpa_mall.constant.OrderStatus;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.entity.Order;
import com.springboot_jpa_mall.entity.OrderItem;
import com.springboot_jpa_mall.repository.MemberRepository;
import com.springboot_jpa_mall.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class OrderApiController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/api/order/history")
    public getAllItemsResponse allOrders() {
        List<Order> orders= orderRepository.findAll();
        List<OrderDTO2> OrderResults = orders.stream()
                        .map(order -> new OrderDTO2(order))
                        .collect(Collectors.toList());

        return new getAllItemsResponse(OrderResults.size(), OrderResults);
    }

    @Data
    static class OrderDTO2 {
        private Long orderId;
        private String memberLoginId;
        private OrderStatus orderStatus;
        private Integer totalAmount;
        private LocalDateTime createdDate;
        private List<OrderItemDto2> orderItems;


        public OrderDTO2(Order order) {
            this.orderId = order.getId();
            this.memberLoginId = order.getMember().getMemberLoginId();
            this.orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto2(orderItem))
                    .collect(Collectors.toList());
            this.createdDate = order.getCreatedDate();
            this.orderStatus = order.getOrderStatus();
            this.totalAmount = order.getTotalAmount();
        }
    }

    @Data
    static class OrderItemDto2 {

        private String itemName;
        private Integer count;
        private Integer amount;

        public OrderItemDto2 (OrderItem orderItem){
            this.itemName = orderItem.getItem().getItemName();
            this.count = orderItem.getCount();
            this.amount = orderItem.getAmount();
        }
    }

    @GetMapping("/api/order/history/{memberLoginId}")
    public getAllItemsResponse orderHistory(@PathVariable String memberLoginId) {
        Member member = memberRepository.findByMemberLoginId(memberLoginId).get();
        List<Order> orders= orderRepository.findByMember(member).get();
        return new getAllItemsResponse(orders.size(), orders);
    }

    @Data
    @AllArgsConstructor
    static class getAllItemsResponse<T> {
        private int count;
        private T data;
    }

}
