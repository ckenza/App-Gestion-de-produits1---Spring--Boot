package entities;

public class User {

    private int idUser;
    private String email;


    public User(){}

    public User(int idUser, String email){
        this.idUser = idUser;
        this.email = email;
    }



    public int getId_user() {
        return idUser;
    }

    public void setId_user(int idUser) {
        this.idUser = idUser;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
