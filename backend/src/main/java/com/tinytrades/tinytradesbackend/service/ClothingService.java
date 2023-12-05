package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.NewClothing;
import com.tinytrades.tinytradesbackend.mapper.ProductMapper;
import com.tinytrades.tinytradesbackend.model.User;
import com.tinytrades.tinytradesbackend.model.enums.*;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.product.ProductImage;
import com.tinytrades.tinytradesbackend.repository.ClothingRepository;
import com.tinytrades.tinytradesbackend.repository.specifications.ClothingSearchCriteria;
import com.tinytrades.tinytradesbackend.repository.specifications.ClothingSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClothingService {

    private final ClothingRepository clothingRepository;
    private final UserService userService;
    private final ProductImageService productImageService;

    @Autowired
    public ClothingService(ClothingRepository clothingRepository, UserService userService, ProductImageService productImageService) {
        this.clothingRepository = clothingRepository;
        this.userService = userService;
        this.productImageService = productImageService;
    }

    public List<ProductResponse> findAllClothing() {
        List<Clothing> clothing = clothingRepository.findAll();
        return clothing.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public List<ProductResponse> findAllClothingByUserId(Long userId) {
        List<Clothing> clothing = clothingRepository.findAllByTrader_IdIs(userId);
        return clothing.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public ProductResponse addNewClothing(Long userId, NewClothing newClothing) {
        User user = userService.findUserById(userId);
        Clothing clothing = Clothing.builder()
                .trader(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title(newClothing.title())
                .gender(Gender.valueOf(newClothing.gender()))
                .condition(ConditionType.valueOf(newClothing.condition()))
                .ageGroup(AgeGroup.valueOf(newClothing.ageGroup()))
                .description(newClothing.description())
                .tags(newClothing.tags())
                .status(Status.ACTIVE)
                .size(ClothingSize.valueOf(newClothing.size()))
                .color(ClothingColor.valueOf(newClothing.color()))
                .clothingCategory(ClothingCategory.valueOf(newClothing.clothingCategory()))
                .build();

        Clothing savedClothing = clothingRepository.save(clothing);

        if (newClothing.imageLinks() != null) {
            for (String imageUrl : newClothing.imageLinks()) {
                ProductImage image = new ProductImage();
                image.setUrl(imageUrl);
                image.setClothing(savedClothing);
                productImageService.saveProductImage(image);
            }
        }

        return ProductMapper.mapToProductResponse(savedClothing);
    }

    public ClothingResponse findClothingById(Long id) {
        Clothing cloth = clothingRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Clothing not found"));
        return ProductMapper.mapToClothingResponse(cloth);
    }

    public List<ProductResponse> searchClothing(ClothingSearchCriteria criteria) {
        Specification<Clothing> spec = new ClothingSpecification(criteria);
        List<Clothing> clothingList = clothingRepository.findAll(spec);
        return clothingList.stream().map(ProductMapper::mapToProductResponse).collect(Collectors.toList());
    }
}
