package me.latanks.ecommerce_api.services;

import me.latanks.ecommerce_api.models.Product;
import me.latanks.ecommerce_api.models.dto.request.CreateProductDTO;
import me.latanks.ecommerce_api.models.dto.request.CreateUserDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateProductDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateUserDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedProductDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedUserDTO;
import me.latanks.ecommerce_api.models.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {

    public CreatedProductDTO create(CreateProductDTO createProductDTO);

    public CreatedProductDTO update(Long id, UpdateProductDTO updateProductDTO);

    public void delete(Long id);

    public List<Product> findAllWithMinAndMaxPrice(BigDecimal minPrice, BigDecimal maxPrice);

    public List<Product> findAllByName(String name);

    public Iterable<Product> findAllByCategory(Category category);

    public Iterable<Product> findAll();

    public CreatedProductDTO findById(Long id);
}
