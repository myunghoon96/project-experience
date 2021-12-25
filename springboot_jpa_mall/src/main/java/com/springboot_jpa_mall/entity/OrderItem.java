package com.springboot_jpa_mall.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter @Setter
@Table(name = "order_item")
public class OrderItem {
    @Column(name = "order_item_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer count;
    private Integer orderPrice;
    private Integer amount;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Builder
    private OrderItem(Item item, Integer count, Integer orderPrice, Integer amount, Order order) {
        this.item = item;
        this.count = count;
        this.orderPrice = orderPrice;
        this.amount = count*orderPrice;
        this.order = order;
    }
}
