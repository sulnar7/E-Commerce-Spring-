package com.example.springecommerce.controller;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.model.Category;
import com.example.springecommerce.model.Product;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")

public class ProductController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    @PostMapping("/save")
    public Product save(
            HttpServletRequest request,
            @Valid @RequestBody ProductDTO product) {
//        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(()->
//        new RuntimeException("Category not found"));
//        product.setCategory(category);
        return productService.save(product,request   );

    }
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
    @GetMapping("/products/{name}")
    public Product getProductByName(@PathVariable String name ) {
        return productService.findByName(name);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public Long delete(@PathVariable Long id) {
        log.info("girmir bura");
         productService.deleteProduct(id);
         return id;
    }

}
