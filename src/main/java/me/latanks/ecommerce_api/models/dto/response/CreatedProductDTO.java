package me.latanks.ecommerce_api.models.dto.response;

import java.math.BigDecimal;

public record CreatedProductDTO(
        String name,
        String description,
        Integer quantity,
        BigDecimal price
) {
}
