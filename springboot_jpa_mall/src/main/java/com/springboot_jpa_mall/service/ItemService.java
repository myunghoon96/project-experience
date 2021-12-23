package com.springboot_jpa_mall.service;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.repository.ItemRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Transactional
    public Item addItem(ItemDto itemDto) {
        Item newItem = Item.builder()
                .itemName(itemDto.getItemName())
                .itemPrice(itemDto.getItemPrice())
                .itemStock(itemDto.getItemStock())
                .itemStatus(ItemStatus.SELL)
//                .itemStatus(itemDto.getItemStatus())
                .build();
        log.info(itemDto.getItemName());

        itemRepository.save(newItem);
        return newItem;
    }

}
