package me.latanks.ecommerce_api.models.dto.request;

public record UpdateUserDTO(
    String username,
    String fullName,
    String password,
    String email,
    String phone,
    String address,
    String city,
    String state
) {
}
