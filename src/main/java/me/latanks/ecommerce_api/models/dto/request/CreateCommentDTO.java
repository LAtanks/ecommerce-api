package me.latanks.ecommerce_api.models.dto.request;

public record CreateCommentDTO(
        Byte stars,
        String comment,
        Long productId
) {
}
