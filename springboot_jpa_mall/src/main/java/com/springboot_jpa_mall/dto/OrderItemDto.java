package com.springboot_jpa_mall.dto;

import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.entity.Order;
import com.springboot_jpa_mall.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
public class OrderItemDto {

    private Long id;
    private Item item;
    private Integer count;
    private Integer amount;
    private Order order;

    private OrderItem toEntity(Item item, Integer count, Integer amount, Order order) {
        OrderItem orderItem = OrderItem.builder()
                .count(count)
                .item(item)
                .amount(amount)
                .order(order)
                .build();
        return orderItem;
    }

    public OrderItemDto(OrderItem orderItem){
        this.item = orderItem.getItem();
        this.order = orderItem.getOrder();
        this.amount = orderItem.getAmount();
        this.count = orderItem.getCount();
    }
}
