package me.latanks.ecommerce_api.models.dto.mapper;

import me.latanks.ecommerce_api.models.User;
import me.latanks.ecommerce_api.models.dto.request.CreateUserDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateUserDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreateUserDTO dto);
    User toEntity(UpdateUserDTO dto);

    CreatedUserDTO toDTO(User user);
}
