package me.latanks.ecommerce_api.controllers;


import me.latanks.ecommerce_api.models.Product;
import me.latanks.ecommerce_api.models.dto.request.CreateProductDTO;
import me.latanks.ecommerce_api.models.dto.request.UpdateProductDTO;
import me.latanks.ecommerce_api.models.dto.response.CreatedProductDTO;
import me.latanks.ecommerce_api.models.enums.Category;
import me.latanks.ecommerce_api.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreatedProductDTO> create(@RequestBody CreateProductDTO obj){
        return ResponseEntity.ok(productService.create(obj));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreatedProductDTO> update(@PathVariable("id") Long id, @RequestBody UpdateProductDTO obj){
        return ResponseEntity.ok(this.productService.update(id, obj));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{category}")
    public ResponseEntity<Iterable<Product>> findAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.findAllByCategory(Category.valueOf(category)));
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/byName/")
    public ResponseEntity<Iterable<Product>> findAllByName(@RequestParam String name){
        return ResponseEntity.ok(productService.findAllByName(name));
    }

    @GetMapping("/byValue/")
    public ResponseEntity<Iterable<Product>> findAllWithMinAndMaxPrice(@RequestParam BigDecimal min,  @RequestParam BigDecimal max){
        return ResponseEntity.ok(productService.findAllWithMinAndMaxPrice(min, max));
    }
}
