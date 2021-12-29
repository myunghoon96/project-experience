package com.springboot_jpa_mall.controller;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.entity.Image;
import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.repository.ImageRepository;
import com.springboot_jpa_mall.repository.ItemRepository;

import com.springboot_jpa_mall.repository.ItemWithImage;
import com.springboot_jpa_mall.service.ImageService;
import com.springboot_jpa_mall.service.ItemService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@Getter
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ImageService imageService;

    @GetMapping("")
    public String getAllItems(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 100);
        Page<ItemWithImage> pageItems = itemRepository.findBy(pageRequest);
        model.addAttribute("pageItems", pageItems);

        for (ItemWithImage pageItem : pageItems) {
            log.info("{}", pageItem.getImages().get(0).getImageUrl());
        }
        return "item/allItems";
    }

//    @PostConstruct
//    public void initializing() {
//        for (int i = 0; i < 100; i++) {
//            Item item = Item.builder()
//                    .itemName("item " + i)
//                    .itemPrice(i)
//                    .itemStock(i)
//                    .itemStatus(ItemStatus.SELL)
//                    .build();
//            itemRepository.save(item);
//        }
//    }

    @GetMapping(value = "/{itemId}")
    public String itemDetail(Model model, @PathVariable("itemId") Long itemId){
        ItemDto itemDto = itemService.getItemDetail(itemId);
        Integer count = 1;

        List<String> imageUrls = imageService.findImageUrlsByItemId(itemId);

        model.addAttribute("itemDto", itemDto);
        model.addAttribute("count", count);
        model.addAttribute("itemId", itemId);
        model.addAttribute("imageUrls", imageUrls);
        return "item/itemDetail";
    }

    @GetMapping("/new")
    public String addItem(Model model) {
        model.addAttribute("item", new ItemDto());
        return "item/addItemForm";
    }

    @PostMapping("/new")
    public String addItem(@Valid ItemDto itemDto, BindingResult bindingResult, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
        if (bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "item/addItemForm";
        }

        try {
            itemService.addItem(itemDto, itemImgFileList);
        }
        catch (Exception e){
            log.info("fail");
            log.info(e.getMessage());
            log.info("error "+e);
        }

        return "redirect:/";
    }

}
