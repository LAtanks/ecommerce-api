package me.latanks.ecommerce_api.services.impl;

import me.latanks.ecommerce_api.exception.impl.ProductNotFoundException;
import me.latanks.ecommerce_api.models.Product;
import me.latanks.ecommerce_api.models.dto.mapper.ProductMapper;
import me.latanks.ecommerce_api.models.dto.request.CreateProductDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateProductDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedProductDTO;
import me.latanks.ecommerce_api.models.enums.Category;
import me.latanks.ecommerce_api.repositories.IProductRepository;
import me.latanks.ecommerce_api.services.IProductService;
import me.latanks.ecommerce_api.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ProductMapper  productMapper;

    public CreatedProductDTO create(CreateProductDTO obj) {
        Product product = this.productMapper.toEntity(obj);
        return this.productMapper.toDTO(this.productRepository.save(product));
    }

    public CreatedProductDTO update(Long id, UpdateProductDTO obj) {
        Product product = this.productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );

        Product toUpdateproduct = this.productMapper.toEntity(obj);

        PropertiesUtil.copyProperties(product, toUpdateproduct);

        return this.productMapper.toDTO(this.productRepository.save(toUpdateproduct));
    }

    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }

    public List<Product> findAllWithMinAndMaxPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return this.productRepository.findAll().stream()
                .filter(e -> e.getPrice().compareTo(minPrice) > 0)
                .filter(e -> e.getPrice().compareTo(maxPrice)  < 0)
                .toList();
    }

    public List<Product> findAllByName(String name){
        return this.productRepository.findAll().stream()
                .filter(e -> e.getName().contains(name))
                .toList();
    }

    public Iterable<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Iterable<Product> findAllByCategory(Category category) {
        return this.productRepository.findByCategory(category);
    }

    public CreatedProductDTO findById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );

        return this.productMapper.toDTO(product);
    }
}
