package me.latanks.ecommerce_api.models.dto.request;

public record CreateUserDTO(
        String username,
        String fullName,
        String password,
        String email,
        String cpf,
        String phone,
        String address,
        String city,
        String state
) {
}
