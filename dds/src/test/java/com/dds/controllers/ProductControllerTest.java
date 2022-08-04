package com.dds.controllers;

import com.dds.model.Product;
import com.dds.services.ProductService;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductService productService;

    private MockMvc mockMvc;

    @BeforeEach
    void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @SneakyThrows
    @Test
    @DisplayName("GET /products/allProducts success")
    void findAllProducts() {
        Product product = new Product();
        product.setId(10L);
        product.setTitle("Some food");
        product.setDescription("Some description");
        product.setPrice(BigDecimal.valueOf(200.0));

        Product product1 = new Product();
        product1.setId(11L);
        product1.setTitle("Some food");
        product1.setDescription("Some description");
        product1.setPrice(BigDecimal.valueOf(200.0));

        doReturn(Lists.newArrayList(product,product1)).when(productService).findAll();

        mockMvc.perform(get("/products/allProducts"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].title", is("Some food")))
                .andExpect(jsonPath("$[0].description", is("Some description")))
                .andExpect(jsonPath("$[0].price", is(200.0)))
                .andExpect(jsonPath("$[1].id", is(11)))
                .andExpect(jsonPath("$[1].title", is("Some food")))
                .andExpect(jsonPath("$[1].description", is("Some description")))
                .andExpect(jsonPath("$[1].price", is(200.0)));
    }

    @SneakyThrows
    @Test
    @DisplayName("GET /products/id success")
    void testProductFindById() {
        Product product = new Product();
        product.setId(10L);
        product.setTitle("Some food");
        product.setDescription("Some description");
        product.setPrice(BigDecimal.valueOf(200.0));

        doReturn(Optional.of(product)).when(productService).findById(10L);

        mockMvc.perform(get("/products/{id}", 10L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.title", is("Some food")))
                .andExpect(jsonPath("$.description", is("Some description")))
                .andExpect(jsonPath("$.price", is(200.0)));
    }


    @Test
    @DisplayName("PUT /products/id success")
    void update() {
    }

    @Test
    @DisplayName("DELETE /products/id success")
    void delete() {
    }
}