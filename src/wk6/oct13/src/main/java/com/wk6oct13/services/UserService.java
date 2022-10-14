package wk6.oct13.src.main.java.com.wk6oct13.services;

import com.wk6oct13.model.UserModel;
import wk6.oct13.src.main.java.com.wk6oct13.repository.UserRepo;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    // User login
    public UserModel validateUserLogin(String email,String password) throws Exception{
        UserModel user = userRepo.getUserByEmailAndPassword(email,password).orElseThrow(()->new Exception("Login Error!"));
        String token = generateToken(user.getEmail()); // generate token
        updateToken(token,user.getId());
        user.setToken(token);
        return user;
    }

    // User logout
    public boolean userLogout(int user_id) throws Exception{
        updateToken("",user_id);
        return true;
    }

    // Generate token
    private String generateToken(String email) {
        String emailEncoded = Base64.getEncoder().encode(email.getBytes()).toString();
        String token = emailEncoded + System.currentTimeMillis();
        return token;
    }

    // Update token in database
    private void updateToken(String token ,int userId){
        userRepo.updateTokenForUserId(token,userId);
    }

    // Get User by ID
    public UserModel getUser(Integer userId) throws Exception {
        Optional<UserModel> userModel = userRepo.findById(userId);
        if(userModel.isPresent()){
            return userModel.get();
        }else {
            throw new Exception ("User not found!");
        }
    }

    // Get All Users
    public List<UserModel> getUsers() throws Exception {
        return userRepo.findAll();
    }

    // Validate token
    public boolean validateToken(String token,Integer userId) throws  Exception{
        UserModel user = getUser(userId);
        if(user.getToken().equals(token)){
            return  true;
        }else{
            throw new Exception("token mismatch");
        }
    }

}
