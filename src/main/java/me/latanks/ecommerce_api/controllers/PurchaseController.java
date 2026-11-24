package me.latanks.ecommerce_api.controllers;

import me.latanks.ecommerce_api.models.Purchase;
import me.latanks.ecommerce_api.services.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseServiceImpl  purchaseService;

    @PatchMapping("/addToCart/{id}")
    public ResponseEntity<Void> addProductToCart(@PathVariable("id") Long productId){
        this.purchaseService.addProductToCart(productId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/removeFromCart/{id}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable("id") Long productId){
        this.purchaseService.removeProductFromCart(productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Purchase> buyProductsFromCart(Long productId){
        return ResponseEntity.ok(this.purchaseService.buyProductsFromCart());
    }
}
