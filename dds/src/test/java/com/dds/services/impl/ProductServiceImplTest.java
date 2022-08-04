package com.dds.services.impl;

import com.dds.model.Product;
import com.dds.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("Test injecting ProductService Success")
    public void testInjectProductService() throws Exception {
        assertThat(productService).isNotNull();
    }

    @Test
    @DisplayName("Test findByID Success")
    void testFindById() {

        Product product = new Product();
        product.setId(10L);
        product.setTitle("Some food");
        product.setDescription("Some description");
        product.setPrice(BigDecimal.valueOf(200.0));

        doReturn(Optional.of(product)).when(productRepository).findById(10L);

        Optional<Product> returnedProduct = productService.findById(10L);

        Assertions.assertTrue(returnedProduct.isPresent(), "Product was not found");
        Assertions.assertSame(returnedProduct.get(), product, "The product returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(productRepository).findById(1L);

        EmptyResultDataAccessException emptyResultDataAccessException = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            productService.findById(1L);
        }, "Test should throw exception if Id not found");

        Assertions.assertEquals("Product not found!", emptyResultDataAccessException.getMessage());
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
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

        doReturn(Arrays.asList(product, product1)).when(productRepository).findAll();

        List<Product> returnedProducts = productService.findAll();

        Assertions.assertEquals(2, returnedProducts.size(), "findAll should return 2 products!");
    }

    @Test
    @DisplayName("Test save product")
    void testSave() {
        Product product = new Product();
        product.setId(10L);
        product.setTitle("Some food");
        product.setDescription("Some description");
        product.setPrice(BigDecimal.valueOf(200.0));

        doReturn(product).when(productRepository).save(any());

        Product returnedProduct = productService.save(product);

        Assertions.assertNotNull(returnedProduct, "Saved Product should not be null");
    }

}