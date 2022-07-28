package com.dds.services;

import com.dds.model.Product;
import com.dds.model.responses.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    void deleteById(Long id);
}
