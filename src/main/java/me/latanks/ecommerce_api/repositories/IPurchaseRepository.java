package me.latanks.ecommerce_api.repositories;

import me.latanks.ecommerce_api.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRepository extends JpaRepository<Purchase,Long> {
}
