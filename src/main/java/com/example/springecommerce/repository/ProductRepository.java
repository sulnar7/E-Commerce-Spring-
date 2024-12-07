package com.example.springecommerce.repository;

import com.example.springecommerce.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(@NotNull String name);

    List<Product> id(Long id);
}
