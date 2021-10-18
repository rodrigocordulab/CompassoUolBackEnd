package com.compassouol.project.service;

import com.compassouol.project.dto.ProductDTO;
import com.compassouol.project.entities.Product;
import com.compassouol.project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll() {
        List<Product> result = repository.findAll();
        return result.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public Optional<Product> getById(long id) {
        return repository.findById(id);
    }

    public List<ProductDTO> getBySearch(String query, Double minPrice, Double maxPrice) {
        List<Product> result = repository.getBySearchQuery(query, minPrice, maxPrice);
        return result.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public ResponseEntity<Object> deleteById(long id) {
        Optional<Product> result = repository.findById(id);
        if (result.isPresent()) {
            repository.delete(result.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Product> putById(long id, Product newProduct) {
        Optional<Product> oldProduct = repository.findById(id);
        if (oldProduct.isPresent()) {
            Product product = oldProduct.get();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());
            repository.save(product);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
