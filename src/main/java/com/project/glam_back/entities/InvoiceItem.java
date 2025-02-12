package com.project.glam_back.entities;

public class InvoiceItem {


    private int idInvoice;
    private int idProduct;
    private int quantity;



    public InvoiceItem(){}

    public InvoiceItem(int idInvoice, int idProduct, int quantity){
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
        this.quantity = quantity;
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
}
