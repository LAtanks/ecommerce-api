package me.latanks.ecommerce_api.models.dto.mapper;

import me.latanks.ecommerce_api.models.Product;
import me.latanks.ecommerce_api.models.dto.request.CreateProductDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateProductDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(CreateProductDTO dto);
    Product toEntity(UpdateProductDTO dto);

    CreatedProductDTO toDTO(Product product);
}
