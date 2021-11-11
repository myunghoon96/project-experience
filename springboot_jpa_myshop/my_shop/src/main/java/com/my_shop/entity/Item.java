package com.my_shop.entity;

import com.my_shop.constant.ItemStatus;
import com.my_shop.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@Entity @Table(name = "item")
public class Item extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Lob
    @Column(nullable = false)
    private String detail;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stock = itemFormDto.getStock();
        this.detail = itemFormDto.getDetail();
        this.itemStatus = itemFormDto.getItemStatus();
    }
}
