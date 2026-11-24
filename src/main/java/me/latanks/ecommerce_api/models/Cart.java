package me.latanks.ecommerce_api.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    private Long  id;
    @ManyToOne
    private List<Product> product = new ArrayList<>();
    @OneToOne
    private User user;

    public Cart(Long id, List<Product> product, User user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public Cart() {
    }

    public void addProductToCart(Product product) {
        this.product.add(product);
    }

    public void removeProductFromCart(Product product) {
        this.product.remove(product);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
