package com.springboot_jpa_mall.entity;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@Getter @Setter
@Table(name = "item")
public class Item extends BaseEntity {
    @Column(name = "item_id")
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_price")
    private Integer itemPrice;
    @Column(name = "item_stock")
    private Integer itemStock;

    @Column(name = "item_status")
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Builder
    public Item(String itemName, Integer itemPrice, Integer itemStock, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.itemStatus = itemStatus;
    }

    public OrderItem createOrderItem(Item item, Integer orderPrice, Integer count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setAmount(orderPrice*count);
        orderItem.setCount(count);
        return orderItem;
    }

    public void subtractItemStock(Integer count) {
        this.itemStock = this.itemStock-count;
    }

}
