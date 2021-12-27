package com.springboot_jpa_mall.dto;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.entity.Image;
import com.springboot_jpa_mall.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {

    @NotBlank
    private String itemName;
    @NotNull
    private Integer itemPrice;
    @NotNull
    @Min(0) @Max(10000)
    private Integer itemStock;
//    @NotNull
    private ItemStatus itemStatus;

    @Builder
    public ItemDto(String itemName, Integer itemPrice, Integer itemStock, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.itemStatus = itemStatus;
    }

    public Item toEntity() {
        List< Image > images = new ArrayList<>();
        return Item.builder()
                .itemName(this.itemName)
                .itemPrice(this.itemPrice)
                .itemStock(this.itemStock)
                .itemStatus(this.itemStatus)
                .images(images)
                .build();
    }

}
