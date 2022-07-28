package com.dds.services.impl;

import com.dds.model.Product;
import com.dds.model.responses.ProductResponse;
import com.dds.repositories.ProductRepository;
import com.dds.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public List<ProductResponse> findAll() {
        List<Product> all = productRepository.findAll();
        List<ProductResponse> list = new ArrayList<>();
        for (Product product : all) {
            list.add(new ProductResponse(product));
        }
        return list;
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
