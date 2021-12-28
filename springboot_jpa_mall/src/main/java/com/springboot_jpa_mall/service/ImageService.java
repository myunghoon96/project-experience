package com.springboot_jpa_mall.service;

import com.springboot_jpa_mall.entity.Image;
import com.springboot_jpa_mall.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    FileService fileService;

    public Image saveImage(Image itemImg, MultipartFile itemImgFile) throws Exception{
        String originalFilename = itemImgFile.getOriginalFilename();
        String imageName = "";
        String imageUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(originalFilename)){
//            imageName = fileService.uploadFile("C:/Users/NKNK-DESKTOP/Desktop/project-experience/springboot_jpa_mall/src/main/resources/static/images/", originalFilename,
//                    itemImgFile.getBytes());
            imageName = fileService.uploadFile("/Users/nknk/Desktop/project-experience/springboot_jpa_mall/src/main/resources/static/images/", originalFilename,
                    itemImgFile.getBytes());
            imageUrl = "/images/" + imageName;
        }

        //상품 이미지 정보 저장
        itemImg.setOriginalImageName(originalFilename);
        itemImg.setImageName(imageName);
        itemImg.setImageUrl(imageUrl);
        imageRepository.save(itemImg);

        return itemImg;
    }

    public Image findImageByItemId(Long itemId) {
        Optional<Image> imageWrapper = imageRepository.findTop1ByItemId(itemId);
        Image image = imageWrapper.get();
        return image;
    }

    public List<String> findImageUrlsByItemId(Long itemId) {
        List<Image> images = imageRepository.findAllByItemId(itemId);
        List<String> imageUrls = new ArrayList<>();

        log.info("findImageUrlsByItemId");

        for (Image image : images) {
            imageUrls.add(image.getImageUrl());
            log.info("{}", image.getImageUrl());
        }

        return imageUrls;
    }

}
