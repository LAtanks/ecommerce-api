package me.latanks.ecommerce_api.models.dto.response;

import me.latanks.ecommerce_api.models.enums.Roles;

public record LoggedDTO (
        String token,
        String username,
        String email,
        String roles
){
}
