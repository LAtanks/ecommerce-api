package me.latanks.ecommerce_api.services.impl;

import me.latanks.ecommerce_api.exception.impl.ProductNotFoundException;
import me.latanks.ecommerce_api.models.Cart;
import me.latanks.ecommerce_api.models.Purchase;
import me.latanks.ecommerce_api.models.User;
import me.latanks.ecommerce_api.models.enums.Situation;
import me.latanks.ecommerce_api.repositories.ICartRepository;
import me.latanks.ecommerce_api.repositories.IProductRepository;
import me.latanks.ecommerce_api.repositories.IPurchaseRepository;
import me.latanks.ecommerce_api.services.IPurchaseService;
import me.latanks.ecommerce_api.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IPurchaseRepository purchaseRepository;


    @Override
    public void addProductToCart(Long productId) {
        User user = AuthenticationUtil.getInstance().getUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElse(null);

        if(cart == null) {
            cart = new Cart();
            cart.setUser(user);
            this.cartRepository.save(cart);
        }

        cart.addProductToCart(this.productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        ));
    }

    @Override
    public void removeProductFromCart(Long productId) {
        User user = AuthenticationUtil.getInstance().getUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElse(null);

        if(cart == null) {
            this.cartRepository.save(cart);
            cart.setUser(user);
        }

        cart.removeProductFromCart(this.productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        ));
    }

    @Override
    public Purchase buyProductsFromCart() {
        User user = AuthenticationUtil.getInstance().getUser();
        Cart cart = cartRepository.findByUserId(user.getId()).orElse(null);

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setCart(cart);
        purchase.setSituation(Situation.APPROVED);

        return this.purchaseRepository.save(purchase);
    }
}
