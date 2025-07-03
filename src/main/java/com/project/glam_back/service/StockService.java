package com.project.glam_back.service;

import com.project.glam_back.daos.ProductDao;
import com.project.glam_back.entities.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    private final ProductDao productDao;

    public StockService(ProductDao productDao){
        this.productDao = productDao;
    };

    public boolean isStockAvailable(int idProduct, int stock) {
        try {
            Product product = productDao.findById(idProduct);
            return product.getStock() >= stock;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean decreaseStock (int id, int quantity){
        try {
            Product product = productDao.findById(id);
            if(product.getStock() >= quantity){
                product.setStock(product.getStock() - quantity);
                productDao.update(product.getId(), product);
                return true;
            }
        } catch (RuntimeException e) {
            return false;
        }
        return false;
    }

    public void increaseStock(int idProduct, int quantity){
        try {
            Product product = productDao.findById(idProduct);
            product.setStock(product.getStock() + quantity);
            productDao.update(product.getId(), product);
        } catch (RuntimeException e){

        }
    }
}
