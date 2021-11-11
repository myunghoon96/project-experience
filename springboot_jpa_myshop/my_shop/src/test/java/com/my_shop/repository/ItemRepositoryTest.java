package com.my_shop.repository;

import com.my_shop.constant.ItemStatus;
import com.my_shop.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장")
    public void createItemTest(){
        Item item = new Item();
        item.setItemName("item1");
        item.setDetail("item1 detail");
        item.setPrice(10000);
        item.setStock(100);
        item.setItemStatus(ItemStatus.SELL);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        Item savedItem = itemRepository.save(item);

        assertEquals(item.getId(), savedItem.getId());
        assertEquals(item.getDetail(), savedItem.getDetail());
    }

    @Test
    @DisplayName("상품 이름으로 조회")
    public void findItemByNameTest(){
        for (int i=1; i<5; i++) {
            Item item = new Item();
            item.setItemName("item" + i);
            item.setDetail("item" + i + "detail");
            item.setPrice(10000);
            item.setStock(100);
            item.setItemStatus(ItemStatus.SELL);

            Item savedItem = itemRepository.save(item);
        }

        Item findItem = itemRepository.findByItemName("item1");
        assertEquals("item1", findItem.getItemName());

        Item findItem2 = itemRepository.findByItemName("item2");
        assertEquals("item2", findItem2.getItemName());

    }


}