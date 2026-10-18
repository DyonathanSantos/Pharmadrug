package com.pharmacy.service;


import com.pharmacy.model.Product;
import com.pharmacy.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return  productRepository.findAll();
    }

    public Product register(Product product) {
        return productRepository.save(product);
    }
}
