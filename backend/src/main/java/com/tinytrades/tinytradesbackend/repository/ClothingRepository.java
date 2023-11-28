package com.tinytrades.tinytradesbackend.repository;

import com.tinytrades.tinytradesbackend.model.product.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
    List<Clothing> findAllByTrader_IdIs(Long userId);
}
