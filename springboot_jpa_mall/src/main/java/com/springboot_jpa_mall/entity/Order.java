package com.springboot_jpa_mall.entity;

import com.springboot_jpa_mall.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter @Setter
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_status") @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "total_amount")
    private Integer totalAmount;

    public Integer getTotalAmount() {
        Integer sum = 0;

        for (OrderItem orderItem : orderItems) {
            sum += orderItem.getAmount();
        }
        return sum;
    }

    @Builder
    private Order (Member member, List<OrderItem> orderItems, OrderStatus orderStatus, Integer totalAmount) {
        this.member = member;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }
}
