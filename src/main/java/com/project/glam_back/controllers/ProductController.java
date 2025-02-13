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



    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable int idProduct) {
        return ResponseEntity.ok(productDao.findById(idProduct));
    }



    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productDao.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }



    @PutMapping("/{idProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable int idProduct, @RequestBody Product product) {
        Product updatedProduct = productDao.update(idProduct, product);
        return ResponseEntity.ok(updatedProduct);
    }



    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int idProduct) {
        if (productDao.delete(idProduct)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
