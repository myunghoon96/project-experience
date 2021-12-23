package com.springboot_jpa_mall.dto;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.entity.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemDto {

    @NotBlank
    private String itemName;
    @NotNull
    private Integer itemPrice;
    @NotNull
    @Min(0) @Max(100)
    private Integer itemStock;
//    @NotNull
    private ItemStatus itemStatus;

    public Item toEntity(ItemDto itemDto) {
        return Item.builder()
                .itemName(this.itemName)
                .itemPrice(this.itemPrice)
                .itemStock(this.itemStock)
                .itemStatus(this.itemStatus)
                .build();
    }

}
