package com.my_shop.dto;

import com.my_shop.entity.Cart;
import com.my_shop.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class CartItemDto {

    @NotNull(message = "상품 아이디는 필수 값입니다")
    private Long itemId;

    @NotNull(message = "최소 1개 이상 담아주세요")
    private int count;

}
