package com.dds.services;

import com.dds.model.Product;
import com.dds.model.responses.ShoppingCartResponse;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartResponse findAllProducts();
    ShoppingCartResponse addProduct(Long productId);
    ShoppingCartResponse removeProduct(Long productId);

    ShoppingCartResponse removeAllProducts();
}
