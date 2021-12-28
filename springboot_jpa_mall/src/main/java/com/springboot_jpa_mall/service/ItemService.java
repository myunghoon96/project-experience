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
    public Long addItem(ItemDto itemDto, List<MultipartFile> itemImgFileList) throws Exception {
        log.info("addItem");

        log.info("{}", itemDto.toString());

        Item newItem = itemDto.toEntity();
        newItem.setItemStatus(ItemStatus.SELL);

        log.info(itemDto.getItemName());

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            Image itemImg = new Image();
            itemImg.setItem(newItem);
            newItem.getImages().add(itemImg);

            Image savedImage = imageService.saveImage(itemImg, itemImgFileList.get(i));

        }

        itemRepository.save(newItem);
        return newItem.getId();
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
