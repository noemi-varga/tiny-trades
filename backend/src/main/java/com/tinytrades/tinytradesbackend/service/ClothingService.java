package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.clothing.ClothingResponse;
import com.tinytrades.tinytradesbackend.mapper.ClothingMapper;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.repository.ClothingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingService {

    private final ClothingRepository clothingRepository;

    @Autowired
    public ClothingService(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    public List<ClothingResponse> findAllClothing() {
        List<Clothing> clothing = clothingRepository.findAll();
        return clothing.stream().map(ClothingMapper::mapToUserResponse).toList();
    }
}
