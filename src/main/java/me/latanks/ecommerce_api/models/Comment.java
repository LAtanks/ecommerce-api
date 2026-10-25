package me.latanks.ecommerce_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 5)
    private Byte stars;

    private String comment;

    @OneToMany(fetch = FetchType.LAZY)
    private Long productId;
}
