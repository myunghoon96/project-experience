package com.springboot_jpa_mall.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter
public class ImageDto {

    private String imageName;
    private String originalImageName;
    private String imageUrl;

}
