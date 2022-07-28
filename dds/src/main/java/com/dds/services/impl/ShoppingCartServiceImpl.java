package com.dds.services.impl;

import com.dds.model.Product;
import com.dds.model.ShoppingCart;
import com.dds.model.responses.ShoppingCartResponse;
import com.dds.services.ProductService;
import com.dds.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Optional;

@Service
@SessionScope
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCart shoppingCart;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public ShoppingCartResponse findAllProducts() {
        return modelMapper.map(shoppingCart, ShoppingCartResponse.class);
    }

    @Override
    public ShoppingCartResponse addProduct(Long productId) {
        Optional<Product> product = productService.findById(productId);
        shoppingCart.getProductList().add(product.get());
        return modelMapper.map(shoppingCart, ShoppingCartResponse.class);
    }

    @Override
    public ShoppingCartResponse removeProduct(Long productId) {
        Optional<Product> product = productService.findById(productId);
        if (shoppingCart.getProductList().contains(product.get())) {
            shoppingCart.getProductList().remove(product.get());
        }
        return modelMapper.map(shoppingCart, ShoppingCartResponse.class);
    }

    @Override
    public ShoppingCartResponse removeAllProducts() {
        if (!shoppingCart.getProductList().isEmpty()) {
            shoppingCart.setProductList(new ArrayList<>());
        }
        return modelMapper.map(shoppingCart, ShoppingCartResponse.class);
    }
}
