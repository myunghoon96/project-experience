package com.springboot_jpa_mall.service;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.entity.Image;
import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.repository.ItemRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ImageService imageService;

    @Transactional
    public Item addItem(ItemDto itemDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item newItem = Item.builder()
                .itemName(itemDto.getItemName())
                .itemPrice(itemDto.getItemPrice())
                .itemStock(itemDto.getItemStock())
                .itemStatus(ItemStatus.SELL)
//                .itemStatus(itemDto.getItemStatus())
                .build();
        log.info(itemDto.getItemName());

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            Image itemImg = new Image();
            itemImg.setItem(newItem);

            imageService.saveImage(itemImg, itemImgFileList.get(i));
        }

        itemRepository.save(newItem);
        return newItem;
    }

    public ItemDto getItemDetail(Long itemId) {

        Optional<Item> findItemWrapper = itemRepository.findById(itemId);
        Item findItem = findItemWrapper.get();

        ItemDto itemDto = ItemDto.builder()
                .itemName(findItem.getItemName())
                .itemPrice(findItem.getItemPrice())
                .itemStock(findItem.getItemStock())
                .itemStatus(findItem.getItemStatus())
                .build();

        return itemDto;

    }
}
