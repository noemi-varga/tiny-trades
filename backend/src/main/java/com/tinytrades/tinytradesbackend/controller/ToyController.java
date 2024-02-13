package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.UpdateToy;
import com.tinytrades.tinytradesbackend.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/toy")
public class ToyController {
    private final ToyService toyService;

    @Autowired
    public ToyController(ToyService toyService) {
        this.toyService = toyService;
    }

    @GetMapping()
    public List<ProductResponse> findAllToys() {
        return toyService.findAllToys();
    }

    @GetMapping("/{id}")
    public ToyResponse findToyById(@PathVariable Long id) {
        return toyService.findToyById(id);
    }

    @GetMapping("/users/{userId}")
    public List<ProductResponse> findAllToyByUserId(@PathVariable Long userId) {
        return toyService.findAllToysByUserId(userId);
    }

    @PostMapping("/users/{userId}")
    public ProductResponse addNewToy(@PathVariable Long userId, @RequestBody NewToy newToy){
        return toyService.addNewToy(userId, newToy);
    }

    @PutMapping("/{toyId}/users/{userId}/")
    public ProductResponse updateToy(@PathVariable Long userId, @PathVariable Long toyId, @RequestBody UpdateToy updateToy){
        return toyService.updateToy(userId, toyId, updateToy);
    }


}
