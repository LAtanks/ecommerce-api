package me.latanks.ecommerce_api.services;

import me.latanks.ecommerce_api.models.Product;
import me.latanks.ecommerce_api.models.Purchase;

public interface IPurchaseService {

    void addProductToCart(Long productId);
    void removeProductFromCart(Long productId);
    Purchase buyProductsFromCart();
}
