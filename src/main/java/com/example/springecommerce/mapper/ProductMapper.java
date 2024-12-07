package com.example.springecommerce.mapper;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.model.Category;
import com.example.springecommerce.model.Product;
import com.example.springecommerce.repository.CategoryRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class ProductMapper {
    private final CategoryRepository categoryRepository;

    public Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        log.info(product.toString(), "category");

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return product;
    }
}
