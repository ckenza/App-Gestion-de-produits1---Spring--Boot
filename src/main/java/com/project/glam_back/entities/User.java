package com.project.glam_back.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class User {

    @NotNull
    private int idUser;

    @Email(message = "le format de l'email n'est pas conforme")
    private String email;


    public User(){}

    public User(int idUser, String email){
        this.idUser = idUser;
        this.email = email;
    }



    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
