package com.springboot_jpa_mall.controller;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.repository.ItemRepository;
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

@Slf4j
@Controller
@Getter
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public String getAllItems(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 100);
        Page<Item> pageItems = itemRepository.findAll(pageRequest);
        model.addAttribute("pageItems", pageItems);

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
        model.addAttribute("item", itemDto);
        model.addAttribute("count", count);
        model.addAttribute("itemId", itemId);
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
            log.info(e.getMessage());
        }

        return "redirect:/";
    }

}
