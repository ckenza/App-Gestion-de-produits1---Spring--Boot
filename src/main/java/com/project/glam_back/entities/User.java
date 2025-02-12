package com.project.glam_back.entities;

public class User {

    private int idUser;
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
