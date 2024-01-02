package com.tinytrades.tinytradesbackend.repository;

import com.tinytrades.tinytradesbackend.model.image.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
