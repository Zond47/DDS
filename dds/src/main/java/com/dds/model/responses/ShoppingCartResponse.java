package com.dds.model.responses;

import com.dds.model.enums.OrderStatus;
import com.dds.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartResponse {
    private List<Product> productList;
    private Double price;
    private OrderStatus status;
}
