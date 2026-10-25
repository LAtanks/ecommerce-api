package me.latanks.ecommerce_api.repositories;

import me.latanks.ecommerce_api.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
}
