package com.project.glam_back.controllers;

import com.project.glam_back.daos.ProductDao;
import com.project.glam_back.entities.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productDao.findAll());
    }


    @GetMapping("/search")
    public ResponseEntity<List<Product>>  searchProduct(@RequestParam String query){
        return ResponseEntity.ok(productDao.searchProduct(query));
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productDao.findById(id));
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productDao.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = productDao.update(id, product);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (productDao.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
