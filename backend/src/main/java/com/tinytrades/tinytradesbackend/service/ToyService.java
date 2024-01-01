package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.mapper.ProductMapper;
import com.tinytrades.tinytradesbackend.model.User;
import com.tinytrades.tinytradesbackend.model.enums.*;
import com.tinytrades.tinytradesbackend.model.product.ProductImage;
import com.tinytrades.tinytradesbackend.model.product.Toy;
import com.tinytrades.tinytradesbackend.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ToyService {

    private final ToyRepository toyRepository;

    private final UserService userService;

    private final ProductImageService productImageService;

    @Autowired
    public ToyService(ToyRepository toyRepository, UserService userService, ProductImageService productImageService) {
        this.toyRepository = toyRepository;
        this.userService = userService;
        this.productImageService = productImageService;
    }

    public List<ProductResponse> findAllToys() {
        List<Toy> toys = toyRepository.findAll();
        return toys.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public List<ProductResponse> findAllClothingByUserId(Long userId) {
        List<Toy> toys = toyRepository.findAllByTrader_IdIs(userId);
        return toys.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public ProductResponse addNewToy(Long userId, NewToy newToy) {
        User user = userService.findUserById(userId);
        Toy toy = Toy.builder()
                .trader(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title(newToy.title())
                .gender(Gender.valueOf(newToy.gender()))
                .condition(ConditionType.valueOf(newToy.condition()))
                .ageGroup(AgeGroup.valueOf(newToy.ageGroup()))
                .description(newToy.description())
                .tags(newToy.tags())
                .status(Status.ACTIVE)
                .toyCategory(ToyCategory.valueOf(newToy.toyCategory()))
                .build();

        if (newToy.imageLinks() != null) {
            for (String imageUrl : newToy.imageLinks()) {
                ProductImage image = new ProductImage();
                image.setUrl(imageUrl);
                toy.addImage(image);
            }
        }

        Toy savedToy = toyRepository.save(toy);

        return ProductMapper.mapToProductResponse(savedToy);
    }

    public ToyResponse findToyById(Long id) {
        Toy toy = toyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Toy not found"));
        return ProductMapper.mapToToyResponse(toy);
    }
}
