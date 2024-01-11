package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.UpdateToy;
import com.tinytrades.tinytradesbackend.mapper.ProductMapper;
import com.tinytrades.tinytradesbackend.model.user.User;
import com.tinytrades.tinytradesbackend.model.enums.*;
import com.tinytrades.tinytradesbackend.model.image.ProductImage;
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

    @Autowired
    public ToyService(ToyRepository toyRepository, UserService userService) {
        this.toyRepository = toyRepository;
        this.userService = userService;
    }

    public List<ProductResponse> findAllToys() {
        List<Toy> toys = toyRepository.findAll();
        return toys.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public List<ProductResponse> findAllToysByUserId(Long userId) {
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

    public ProductResponse updateToy(Long userId, Long id, UpdateToy updateToy) {
        User user = userService.findUserById(userId);
        Toy toy = toyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Toy not found"));

        if (!toy.getTrader().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to update this clothing");
        }

        toy.setUpdatedAt(LocalDateTime.now());
        toy.setTitle(updateToy.title());
        toy.setGender(Gender.valueOf(updateToy.gender()));
        toy.setCondition(ConditionType.valueOf(updateToy.condition()));
        toy.setAgeGroup(AgeGroup.valueOf(updateToy.ageGroup()));
        toy.setDescription(updateToy.description());
        toy.setTags(updateToy.tags());
        toy.setStatus(Status.valueOf(updateToy.status()));
        toy.setToyCategory(ToyCategory.valueOf(updateToy.toyCategory()));

        Toy updatedToy = toyRepository.save(toy);

        return ProductMapper.mapToProductResponse(updatedToy);
    }
}
