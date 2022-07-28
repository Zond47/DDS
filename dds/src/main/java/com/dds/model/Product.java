package com.dds.model;

import lombok.Data;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    private Long Id;

    @NotBlank(message = "Title must not be blank!")
    @Size(min=3, message = "Title must be at least 3 characters long!")
    private String title;

    @NotBlank(message = "Description must not be blank!")
    @Size(min=10, message = "Description must be at least 10 characters long!")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
}
