package com.springboot_jpa_mall.service;

import com.springboot_jpa_mall.entity.Image;
import com.springboot_jpa_mall.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    FileService fileService;

    public void saveImage(Image itemImg, MultipartFile itemImgFile) throws Exception{
        String originalFilename = itemImgFile.getOriginalFilename();
        String imageName = "";
        String imageUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(originalFilename)){
            imageName = fileService.uploadFile("C:/Users/NKNK-DESKTOP/Desktop/image/", originalFilename,
                    itemImgFile.getBytes());
            imageUrl = "/images/" + imageName;
        }

        //상품 이미지 정보 저장
        itemImg.setOriginalImageName(originalFilename);
        itemImg.setImageName(imageName);
        itemImg.setImageUrl(imageUrl);
        imageRepository.save(itemImg);
    }

}
