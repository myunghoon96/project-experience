package com.springboot_jpa_mall.repository;

import com.springboot_jpa_mall.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByItemId(Long id);
    Optional<Image> findTop1ByItemId(Long itemId);

}
