package com.my_shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ItemDto {

    private long id;
    private String itemName;
    private Integer price;
    private String detail;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

}
