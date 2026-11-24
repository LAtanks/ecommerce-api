package me.latanks.ecommerce_api.repositories;

import me.latanks.ecommerce_api.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1")
    Optional<Cart> findByUserId(Long userId);
}
