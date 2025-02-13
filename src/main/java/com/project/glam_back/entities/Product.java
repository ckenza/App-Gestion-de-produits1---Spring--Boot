package com.project.glam_back.entities;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public class Product {

    @NotNull(message = "L'id ne peut pas être null")
    private int idProduct;

    @NotBlank(message = "Entrer le titre du produit")
    @Size(min =3, message = "3 caractères minimum")
    private String titleProduct;

    @URL(message = "le format de l'url n'est pas conforme")
    private String imageUrl;

    @NotNull(message = "Entrer le prix du produit")
    @Positive(message = "Le prix doit être au dessus de zéro")
    private Double price;

    @PositiveOrZero(message = "Le stock doit être supérieur ou égal à 0")
    private int stock;



    public Product(){}

    public Product(int idProduct, String titleProduct, String imageUrl, Double price, int stock){
        this.idProduct = idProduct;
        this.titleProduct = titleProduct;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
    }


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }


    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

