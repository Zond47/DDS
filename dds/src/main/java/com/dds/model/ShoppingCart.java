package com.dds.model;

import com.dds.model.enums.OrderStatus;
import lombok.Data;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class ShoppingCart {
    private Long Id;
    private Long userId;
    private List<Product> productList;
    private Double price;
    private OrderStatus status;

    @PostConstruct
    void init(){
        Id = 1L;
        userId = 1L;
        productList = new ArrayList<>();
        price = 0.0;
        status = OrderStatus.CREATED;
    }
}
