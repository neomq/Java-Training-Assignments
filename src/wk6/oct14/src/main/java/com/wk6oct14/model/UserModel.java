package wk6.oct14.src.main.java.com.wk6oct14.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String email;
    @JsonIgnore
    String password;
    String mobile;
    String address;
    String token;
    String profile_img;

}
