package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ToyController {
    private final ToyService toyService;

    @Autowired
    public ToyController(ToyService toyService) {
        this.toyService = toyService;
    }

    @GetMapping("/toy")
    public List<ProductResponse> findAllToys() {
        return toyService.findAllToys();
    }

    @GetMapping("/toy/{id}")
    public ToyResponse findToyById(@PathVariable Long id) {
        return toyService.findToyById(id);
    }

    @GetMapping("/users/{userId}/toy")
    public List<ProductResponse> findAllToyByUserId(@PathVariable Long userId) {
        return toyService.findAllToysByUserId(userId);
    }

    @PostMapping("/users/{userId}/toy")
    public ProductResponse addNewToy(@PathVariable Long userId, @RequestBody NewToy newToy){
        return toyService.addNewToy(userId, newToy);
    }


}
