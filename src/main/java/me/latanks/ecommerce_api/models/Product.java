package me.latanks.ecommerce_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import me.latanks.ecommerce_api.models.enums.Category;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Category category;

    @PositiveOrZero
    private Integer quantity;

    @Positive
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Set<Comment>  comments;
}
