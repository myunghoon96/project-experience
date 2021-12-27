package com.springboot_jpa_mall.repository;

import com.springboot_jpa_mall.constant.ItemStatus;
import com.springboot_jpa_mall.entity.Image;

import java.util.List;

public interface ItemWithImage {

    Long getId();
    String getItemName();
    Integer getItemPrice();
    Integer getItemStock();
    ItemStatus getItemStatus();
    List<Image> getImages();

    interface Image {
        String getImageUrl();
    }
}
