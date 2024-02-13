package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.UpdateToy;
import com.tinytrades.tinytradesbackend.mapper.ProductMapper;
import com.tinytrades.tinytradesbackend.model.user.User;
import com.tinytrades.tinytradesbackend.model.image.ProductImage;
import com.tinytrades.tinytradesbackend.model.product.Toy;
import com.tinytrades.tinytradesbackend.repository.ToyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ToyService {

    private final ToyRepository toyRepository;

    private final UserService userService;

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
        Toy toy = ProductMapper.mapNewToyToToy(newToy, user);

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
        Toy toy = getToyById(id);
        return ProductMapper.mapToToyResponse(toy);
    }

    public ProductResponse updateToy(Long userId, Long id, UpdateToy updateToy) {
        Toy toy = getToyById(id);

        if (!toy.getTrader().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to update this clothing");
        }

        ProductMapper.setUpdateToyToToy(updateToy, toy);

        Toy updatedToy = toyRepository.save(toy);

        return ProductMapper.mapToProductResponse(updatedToy);
    }

    private Toy getToyById(Long id) {
        return toyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Toy not found"));
    }

}
