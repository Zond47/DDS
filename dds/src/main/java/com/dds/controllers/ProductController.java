package com.dds.controllers;

import com.dds.model.Product;
import com.dds.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @RequestMapping("/allProducts")
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping("/{id:[\\d]+}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@Valid @RequestBody Product product,
                          Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(value = "/{Id:[\\d]+}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@PathVariable Long Id,
                                          @Valid @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(Id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.update(Id, product).isPresent() ? product : null);
    }

    @RequestMapping(value = "/delete/{Id:[\\d]+}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> delete(@PathVariable Long Id) {
        Optional<Product> product = productService.findById(Id);
        if (product.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        productService.deleteById(product.get().getId());
        return ResponseEntity.ok(product.get());
    }
}
