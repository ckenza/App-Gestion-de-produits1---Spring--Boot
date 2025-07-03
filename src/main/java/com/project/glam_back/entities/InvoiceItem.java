package com.project.glam_back.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class InvoiceItem {

    @NotNull
    private int idInvoice;

    @NotNull
    private int idProduct;

    @Positive(message = "Au moins un exemplaire")
    private int quantity;

    private BigDecimal unit_price;


    public InvoiceItem(){}

    public InvoiceItem(int idInvoice, int idProduct, int quantity, BigDecimal unit_price){
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }
}
