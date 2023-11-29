package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.mapper.ProductMapper;
import com.tinytrades.tinytradesbackend.model.User;
import com.tinytrades.tinytradesbackend.model.enums.*;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.product.Toy;
import com.tinytrades.tinytradesbackend.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        Toy savedToy = toyRepository.save(toy);

        return ProductMapper.mapToProductResponse(savedToy);
    }
}
