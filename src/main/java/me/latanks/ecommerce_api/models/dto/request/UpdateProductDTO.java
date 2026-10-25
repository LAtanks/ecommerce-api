package me.latanks.ecommerce_api.models.dto.request;

import me.latanks.ecommerce_api.models.enums.Category;

import java.math.BigDecimal;

public record UpdateProductDTO(
        String name,
        String description,
        Category category,
        Integer quantity,
        BigDecimal price
) {
}
