package me.latanks.ecommerce_api.models.dto.response;

public record CreatedUserDTO(
        String username,
        String email,
        String fullAddress
) {
}
