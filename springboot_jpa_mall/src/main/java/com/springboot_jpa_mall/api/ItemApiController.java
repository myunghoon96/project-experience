package com.springboot_jpa_mall.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.repository.ItemRepository;
import com.springboot_jpa_mall.repository.ItemWithImage;
import com.springboot_jpa_mall.service.ImageService;
import com.springboot_jpa_mall.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ItemApiController {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ImageService imageService;

    @PostMapping("/api/item/new")
    public Object addItem(@RequestParam("itemDtoString") String itemDtoString, @RequestParam("itemImgFileList") List<MultipartFile> itemImgFileList){
        try {
            ObjectMapper mapper = new ObjectMapper();
            ItemDto itemDto = mapper.readValue(itemDtoString, ItemDto.class);

            Long itemId = itemService.addItem(itemDto, itemImgFileList);
            String itemName = itemRepository.findById(itemId).get().getItemName();
            return new addItemResponse(itemId, itemName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    static class addItemResponse {
        Long itemId;
        String itemName;
    }


    @GetMapping("/api/item")
    public getAllItemsResponse getAllItems(){
        PageRequest pageRequest = PageRequest.of(0, 100);
        Page<ItemWithImage> pageItems = itemRepository.findBy(pageRequest);
        List<ItemWithImage> items = pageItems.getContent();
        return new getAllItemsResponse(items.size(), items);
    }

    @Data
    @AllArgsConstructor
    static class getAllItemsResponse<T> {
        private int count;
        private T data;
    }

    @GetMapping("/api/item/{itemId}")
    public getItembyItemIdResponse getItembyItemId(@PathVariable Long itemId){
        ItemDto itemDto = itemService.getItemDetail(itemId);
        List<String> imageUrls = imageService.findImageUrlsByItemId(itemId);
        ItemWithImageUrls itemWithImageUrls = ItemWithImageUrls.builder()
                .id(itemId)
                .itemName(itemDto.getItemName())
                .itemPrice(itemDto.getItemPrice())
                .itemStatus(itemDto.getItemStatus())
                .imageUrls(imageUrls)
                .build();

        return new getItembyItemIdResponse(itemWithImageUrls);

    }
    @Builder
    @Data
    @AllArgsConstructor
    static class ItemWithImageUrls {
        private Long id;
        private String itemName;
        private Integer itemPrice;
        private Integer itemStock;
        private ItemStatus itemStatus;
        private List<String> imageUrls;
    }

    @Data
    @AllArgsConstructor
    static class getItembyItemIdResponse<T> {
        private T data;
    }


}
