package com.my_shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

    private Long id;
    private String itemName;
    private String detail;
    private String imgUrl;
    private Integer price;

    @QueryProjection
    public MainItemDto(Long id, String itemName, String detail, String imgUrl, Integer price) {
        this.id = id;
        this.itemName = itemName;
        this.detail = detail;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
