package wk6.oct11.src.main.java.com.wk6oct11.service;

import com.wk6oct11.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserService {

    public boolean validateUserLogin(String username, String password) throws Exception {

        ArrayList<UserModel> userAccount = new ArrayList<UserModel>();

        userAccount.add(new UserModel("johndoe","12345"));
        userAccount.add(new UserModel("admin","admin123"));
        userAccount.add(new UserModel("tom","qwerty123"));

        for (UserModel acc: userAccount){
            if (username.equals(acc.username) &&
                    password.equals(acc.password)) {
                return true;
            }
        }
        throw new Exception("Error");
    }

    public boolean validateUserLogin2(String email, String password) throws Exception {
        if(email.equals("admin@email.com") && password.equals("admin123")){
            return true;
        } else {
            throw new Exception("Error");
        }
    }

    public String getUserDetail(int userid) {
        try {
            HashMap<Integer,String> users = new HashMap<>();
            users.put(1, "Andy");
            users.put(2, "Bella");
            users.put(3, "Charlie");
            users.put(4, "Denise");
            String name = users.get(userid);
            return name;
        } catch (Exception e){
            return e.getMessage();
        }
    }

}
