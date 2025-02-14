package com.project.glam_back.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

public class Invoice {

    @NotNull
    private int idInvoice;

    @Past(message = "La date doit être au passé")
    private Date date;

    @PositiveOrZero(message = "la valeur doit être au dessus de zéro")
    private Double total;


    public Invoice(){}
    public Invoice(int idInvoice, Date date, Double total){
        this.idInvoice = idInvoice;
        this.date = date;
        this.total = total;
    }


    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
