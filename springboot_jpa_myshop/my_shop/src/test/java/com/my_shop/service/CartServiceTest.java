package com.my_shop.service;

import com.my_shop.constant.ItemStatus;
import com.my_shop.dto.CartItemDto;
import com.my_shop.entity.CartItem;
import com.my_shop.entity.Item;
import com.my_shop.entity.Member;
import com.my_shop.repository.CartItemRepository;
import com.my_shop.repository.ItemRepository;
import com.my_shop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CartService cartService;
    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveSampleItem(){
        Item item = new Item();
        item.setItemName("item1");
        item.setPrice(100);
        item.setDetail("item1 detail");
        item.setItemStatus(ItemStatus.SELL);
        item.setStock(100);
        return itemRepository.save(item);
    }

    public Member saveSampleMember(){
        Member member = new Member();
        member.setEmail("userA@naver.com");
        return memberRepository.save(member);

    }

    @Test
    @DisplayName("장바구니 아이템 추가 테스트")
    public void addCart(){
        Member member = saveSampleMember();
        Item item = saveSampleItem();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(item.getId());
        cartItemDto.setCount(10);

        Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDto.getCount(), cartItem.getCount());

    }

}