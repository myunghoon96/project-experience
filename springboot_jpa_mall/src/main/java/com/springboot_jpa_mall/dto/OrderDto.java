package com.springboot_jpa_mall.dto;

import com.springboot_jpa_mall.constant.OrderStatus;
import com.springboot_jpa_mall.entity.Member;
import com.springboot_jpa_mall.entity.Order;
import com.springboot_jpa_mall.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class OrderDto {

    private Long id;
    private Member member;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private OrderStatus orderStatus;
    private Integer totalAmount;

    public OrderDto(Order order) {
        this.member = order.getMember();
        this.orderItems = order.getOrderItems();
        this.orderStatus = order.getOrderStatus();
        this.totalAmount = order.getTotalAmount();
    }

}
