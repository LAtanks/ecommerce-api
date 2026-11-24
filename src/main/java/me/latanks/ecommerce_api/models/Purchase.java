package me.latanks.ecommerce_api.models;

import jakarta.persistence.*;
import me.latanks.ecommerce_api.models.enums.Situation;

@Entity
@Table(name = "tb_purcheses")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private Situation situation;

    public Purchase() {
    }

    public Purchase(Long id, User user, Cart cart, Situation situation) {
        this.id = id;
        this.user = user;
        this.cart = cart;
        this.situation = situation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
