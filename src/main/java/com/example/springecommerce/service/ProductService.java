package com.example.springecommerce.service;

import com.example.springecommerce.dto.ProductDTO;
import com.example.springecommerce.mapper.ProductMapper;
import com.example.springecommerce.mapper.UserMapper;
import com.example.springecommerce.model.Category;
import com.example.springecommerce.model.Product;
import com.example.springecommerce.model.Role;
import com.example.springecommerce.repository.CategoryRepository;
import com.example.springecommerce.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class ProductService {
    private final UserMapper userMapper;
    private final ProductRepository productRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public Product save(ProductDTO product, HttpServletRequest request) {
        String authorizationHeader= request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Authorization header is incorrect");
        }
        String token = authorizationHeader.substring(7);
//        UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUserPrincipal().getName());
        log.info("token bura girir: {}", token);
//        if (!jwtService.hasRole(token, "ROLE_ADMIN")) {
//            throw new RuntimeException("Access Denied: Only admins can save products");
//        }
        if (!jwtService.isTokenValid(token)) {
            throw new SecurityException("Invalid or expired token");

        }


        Product savedProduct=productMapper.toProduct(product);

        log.info("Product: {}", product);


        return productRepository.save(savedProduct);
    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public int deleteProduct(Long id) {
        productRepository.deleteById(id);
        return 1;
    }

    public int increaseQuantity(Product product, int quantity) {
        product.setQuantity(product.getQuantity() + quantity);
        return 1;
    }

    public int decreaseQuantity(Product product, int quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        return 1;
    }
}