package me.latanks.ecommerce_api.repositories;

import me.latanks.ecommerce_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
