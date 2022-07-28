package com.dds.model.responses;

import com.dds.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long Id;
    private String title;
    private String description;
    private BigDecimal price;

    public ProductResponse(Product product) {
        this.Id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.title = product.getTitle();
    }
}