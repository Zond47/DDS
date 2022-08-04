package com.dds.services.impl;

import com.dds.model.Product;
import com.dds.repositories.ProductRepository;
import com.dds.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Product not found!", 0));
        return Optional.ofNullable(product);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    // TODO Refactor
    public Optional<Product> update(Long id, Product product) {
        //findById(id).ifPresent(product1 -> {productRepository.save(product);});
        if (productRepository.existsById(id)) {
            productRepository.save(product);
        }
        return Optional.ofNullable(product);
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    private void delete(Product product) {
        try {
            productRepository.deleteById(product.getId());
        } catch (EmptyResultDataAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
