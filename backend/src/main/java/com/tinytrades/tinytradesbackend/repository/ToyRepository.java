package com.tinytrades.tinytradesbackend.repository;

import com.tinytrades.tinytradesbackend.model.product.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {
}
