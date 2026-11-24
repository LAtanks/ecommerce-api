package me.latanks.ecommerce_api.services;

import me.latanks.ecommerce_api.models.dto.request.CreateUserDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateUserDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedUserDTO;

public interface IUserService {


    public CreatedUserDTO create(CreateUserDTO createUserDTO);

    public CreatedUserDTO update(UpdateUserDTO createUserDTO);

    public void delete(Long id);

    public void delete();

    public CreatedUserDTO getMyProfile();

    public CreatedUserDTO findById(Long id);

    public CreatedUserDTO findByUsername(String username);

    public CreatedUserDTO findByEmail(String email);
}
