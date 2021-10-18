package com.compassouol.project.controller;

import com.compassouol.project.dto.ProductDTO;
import com.compassouol.project.entities.Product;
import com.compassouol.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable(value = "id") long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<ProductDTO> getBySearch(@RequestParam(value = "q", required = false) String query,
                                        @RequestParam(value = "min_price", required = false) Double minPrice,
                                        @RequestParam(value = "max_price", required = false) Double maxPrice) {

        return service.getBySearch(query, minPrice, maxPrice);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putById(@PathVariable(value = "id") long id, @RequestBody Product product) {
        return service.putById(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") long id) {
        return service.deleteById(id);
    }
}
