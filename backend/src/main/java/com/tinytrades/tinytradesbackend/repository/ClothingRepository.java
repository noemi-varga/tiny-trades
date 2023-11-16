package com.tinytrades.tinytradesbackend.repository;

import com.tinytrades.tinytradesbackend.model.product.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
}
