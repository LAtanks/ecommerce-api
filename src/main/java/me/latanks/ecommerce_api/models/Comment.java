package me.latanks.ecommerce_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 5)
    @NotNull
    private Byte stars;

    @Size(min = 1, max = 255)
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @OneToOne
    private User user;

    public Comment(Long id, Byte stars, String comment, Product product, User user) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.product = product;
        this.user = user;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getStars() {
        return stars;
    }

    public void setStars(Byte stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
