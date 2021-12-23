package com.springboot_jpa_mall.controller;

import com.springboot_jpa_mall.dto.ItemDto;
import com.springboot_jpa_mall.entity.Item;
import com.springboot_jpa_mall.service.ItemService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Slf4j
@Controller
@Getter
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/new")
    public String addItem(Model model) {
        model.addAttribute("itemDto", new ItemDto());
        return "item/addItemForm";
    }

    @PostMapping("/new")
    public String addItem(@Valid ItemDto itemDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "item/addItemForm";
        }

        itemService.addItem(itemDto);

        return "redirect:/";
    }

}
