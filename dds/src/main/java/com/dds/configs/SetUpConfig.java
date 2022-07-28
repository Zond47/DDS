package com.dds.configs;

import com.dds.model.Product;
import com.dds.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


// Hardcoded Config which initialize Test Products and save them to Repository.
@Configuration
@RequiredArgsConstructor
public class SetUpConfig {

    @Autowired
    private final ProductService productService;

    @Bean("product1")
    public Product product1() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Some food");
        product.setDescription("Some description");
        product.setPrice(BigDecimal.valueOf(200.0));
        productService.save(product);
        return product;
    }

    @Bean("product2")
    public Product product2() {
        Product product = new Product();
        product.setId(2L);
        product.setTitle("Some drink");
        product.setDescription("Some description");
        product.setPrice(BigDecimal.valueOf(50.0));
        productService.save(product);
        return product;
    }
}
