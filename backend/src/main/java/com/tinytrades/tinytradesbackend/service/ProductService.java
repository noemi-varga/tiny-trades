package com.tinytrades.tinytradesbackend.service;

import com.tinytrades.tinytradesbackend.dto.product.ProductResponse;
import com.tinytrades.tinytradesbackend.mapper.ProductMapper;
import com.tinytrades.tinytradesbackend.model.product.Product;
import com.tinytrades.tinytradesbackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public List<ProductResponse> findAllProductsByUserId(Long userId) {
        List<Product> products = productRepository.findAllByTrader_IdIs(userId);
        return products.stream().map(ProductMapper::mapToProductResponse).toList();
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)){
            throw new NoSuchElementException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
