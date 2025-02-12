package com.project.glam_back.entities;

public class Product {

    private int idProduct;
    private String titleProduct;
    private String imageUrl;
    private Double price;
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

