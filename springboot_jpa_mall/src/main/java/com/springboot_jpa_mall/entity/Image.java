package com.springboot_jpa_mall.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "image")
@Getter @Setter
public class Image extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "original_image_name")
    private String originalImageName;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Image (String imageName, String originalImageName, String imageUrl) {
        this.imageName = imageName;
        this.originalImageName = originalImageName;
        this.imageUrl = imageUrl;
    }

}
