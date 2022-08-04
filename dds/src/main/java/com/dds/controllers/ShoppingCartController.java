package com.dds.controllers;

import com.dds.model.responses.ShoppingCartResponse;
import com.dds.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {

    @Autowired
    private final ShoppingCartService shoppingCartService;

    @RequestMapping("/allProducts")
    public ResponseEntity<ShoppingCartResponse> findAllProducts() {
        return ResponseEntity.ok(shoppingCartService.findAllProducts());
    }

    @RequestMapping(value = "/addProduct/{id}", method = RequestMethod.POST)
    public ResponseEntity<ShoppingCartResponse> addProduct(@PathVariable("id") Long productId) {
        ShoppingCartResponse response = shoppingCartService.addProduct(productId);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/removeProduct/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ShoppingCartResponse> removeProduct(@PathVariable("id") Long productId) {
        ShoppingCartResponse response = shoppingCartService.removeProduct(productId);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/removeAllProducts", method = RequestMethod.DELETE)
    public ResponseEntity<ShoppingCartResponse> removeAllProducts() {
        ShoppingCartResponse response = shoppingCartService.removeAllProducts();
        return ResponseEntity.ok(response);
    }
}
