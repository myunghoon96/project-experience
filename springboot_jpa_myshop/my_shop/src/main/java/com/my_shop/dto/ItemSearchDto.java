package com.my_shop.dto;

import com.my_shop.constant.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;
    private ItemStatus searchStatus;
    private String searchBy;
    private String searchQuery;


}
