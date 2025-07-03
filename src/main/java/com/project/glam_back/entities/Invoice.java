package com.project.glam_back.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.sql.Date;

public class Invoice {

    @NotNull
    private int id;

    @NotNull
    private int idUser;

    @Past(message = "La date doit être au passé")
    private Date date;

    @PositiveOrZero(message = "la valeur doit être au dessus de zéro")
    private BigDecimal total;


    public Invoice(){}
    public Invoice(int id, int idUser, Date date, BigDecimal total){
        this.id = id;
        this.idUser = idUser;
        this.date = date;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
