package com.project.glam_back.entities;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public class Product {

    @NotNull(message = "L'id ne peut pas être null")
    private int id;

    @NotBlank(message = "Entrer le titre du produit")
    @Size(min =3, message = "3 caractères minimum")
    private String title;

    @URL(message = "le format de l'url n'est pas conforme")
    private String image;

    @NotNull(message = "Entrer le prix du produit")
    @Positive(message = "Le prix doit être au dessus de zéro")
    private BigDecimal price;

    @PositiveOrZero(message = "Le stock doit être supérieur ou égal à 0")
    private int stock;



    public Product(){}

    public Product(int id, String title, String image, BigDecimal price, int stock){
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.stock = stock;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

