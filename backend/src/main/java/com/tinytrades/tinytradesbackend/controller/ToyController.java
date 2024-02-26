package com.tinytrades.tinytradesbackend.controller;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.NewToy;
import com.tinytrades.tinytradesbackend.dto.product.toy.ToyResponse;
import com.tinytrades.tinytradesbackend.dto.product.toy.UpdateToy;
import com.tinytrades.tinytradesbackend.service.ToyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/toy")
@RequiredArgsConstructor
public class ToyController {

    private final ToyService toyService;

    @GetMapping()
    public List<ProductResponse> findAllToys() {
        return toyService.findAllToys();
    }

    @GetMapping("/{id}")
    public ToyResponse findToyById(@PathVariable @NotNull Long id) {
        return toyService.findToyById(id);
    }

    @GetMapping("/users/{userId}")
    public List<ProductResponse> findAllToyByUserId(@PathVariable @NotNull Long userId) {
        return toyService.findAllToysByUserId(userId);
    }

    @PostMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addNewToy(@PathVariable @NotNull Long userId, @RequestBody @Valid NewToy newToy){
        return toyService.addNewToy(userId, newToy);
    }

    @PutMapping("/{toyId}/users/{userId}/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse updateToy(@PathVariable @NotNull Long userId, @PathVariable @Valid Long toyId, @RequestBody UpdateToy updateToy){
        return toyService.updateToy(userId, toyId, updateToy);
    }


}
