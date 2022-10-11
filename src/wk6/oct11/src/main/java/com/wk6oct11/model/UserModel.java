package wk6.oct11.src.main.java.com.wk6oct11.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserModel {

    @Id
    Integer id;
    public String username, password, email, address;

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserModel(String username, String password, String email, String address){
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

}
