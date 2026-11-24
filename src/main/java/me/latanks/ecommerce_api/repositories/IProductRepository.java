package me.latanks.ecommerce_api.repositories;

import me.latanks.ecommerce_api.models.Product;
import me.latanks.ecommerce_api.models.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findByCategory(Category category);
}
