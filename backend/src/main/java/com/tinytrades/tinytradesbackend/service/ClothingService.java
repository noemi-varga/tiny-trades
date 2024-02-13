package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.dto.product.clothing.NewClothing;
import com.tinytrades.tinytradesbackend.dto.product.clothing.UpdateClothing;
import com.tinytrades.tinytradesbackend.mapper.ProductMapper;
import com.tinytrades.tinytradesbackend.model.user.User;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.image.ProductImage;
import com.tinytrades.tinytradesbackend.repository.ClothingRepository;
import com.tinytrades.tinytradesbackend.repository.specifications.ClothingSearchCriteria;
import com.tinytrades.tinytradesbackend.repository.specifications.ClothingSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClothingService {

    private final ClothingRepository clothingRepository;
    private final UserService userService;

    public List<ProductResponse> findAllClothing() {
        List<Clothing> clothing = clothingRepository.findAll(); // TODO paging, sorting
        return clothing.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public List<ProductResponse> findAllClothingByUserId(Long userId) {
        List<Clothing> clothing = clothingRepository.findAllByTrader_IdIs(userId);
        return clothing.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public ProductResponse addNewClothing(Long userId, NewClothing newClothing) {
        User user = userService.findUserById(userId);
        Clothing clothing = ProductMapper.mapNewClothingToClothing(newClothing, user);

        if (newClothing.imageLinks() != null) {
            for (String imageUrl : newClothing.imageLinks()) {
                ProductImage image = new ProductImage();
                image.setUrl(imageUrl);
                clothing.addImage(image);
            }
        }

        Clothing savedClothing = clothingRepository.save(clothing);

        return ProductMapper.mapToProductResponse(savedClothing);
    }

    public ClothingResponse findClothingById(Long id) {
        Clothing cloth = getClothingById(id);
        return ProductMapper.mapToClothingResponse(cloth);
    }

    public List<ProductResponse> searchClothing(ClothingSearchCriteria criteria) {
        Specification<Clothing> spec = new ClothingSpecification(criteria);
        List<Clothing> clothingList = clothingRepository.findAll(spec);
        return clothingList.stream().map(ProductMapper::mapToProductResponse).collect(Collectors.toList());
    }

    public ProductResponse updateClothing(Long userId, Long id, UpdateClothing updateClothing) {
        Clothing clothing = getClothingById(id);

        if (!clothing.getTrader().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to update this clothing");
        }

        ProductMapper.setUpdateClothingToClothing(updateClothing, clothing);

        Clothing updatedClothing = clothingRepository.save(clothing);

        return ProductMapper.mapToProductResponse(updatedClothing);
    }

    private Clothing getClothingById(Long id) {
        return clothingRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Clothing not found"));
    }

}
