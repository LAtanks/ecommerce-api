package me.latanks.ecommerce_api.services;

import me.latanks.ecommerce_api.models.dto.request.LoginDTO;
import me.latanks.ecommerce_api.models.dto.response.LoggedDTO;

public interface IAuthService {

    LoggedDTO login(LoginDTO data);
}
