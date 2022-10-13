package wk6.oct12.src.main.java.com.wk6oct12.services;

import wk6.oct12.src.main.java.com.wk6oct12.repository.UserRepo;
import com.wk6oct12.model.UserModel;
import com.wk6oct12.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    // Register User
    public boolean registerUser(UserRequest userReq) throws Exception {
        try {
            UserModel createUser = new UserModel();

            if (userRepo.findByEmail(userReq.getEmail()) == null){
                createUser.setName(userReq.getName());
                createUser.setEmail(userReq.getEmail());
                createUser.setAddress(userReq.getAddress());
                createUser.setMobile(userReq.getMobile());
                createUser.setPassword(userReq.getPassword());
                userRepo.save(createUser); // insert data in table
                return true;
            } else {
                throw new Exception("Error, user already exists!");
            }
        } catch (Exception e){
            throw e;
        }
    }

    // User login
    public UserModel validateUserLogin(String email,String password) throws Exception{
        UserModel user = userRepo.getUserByEmailAndPassword(email,password).orElseThrow(()->new Exception("Login Error!"));
        return user;
    }

    // Get All Users
    public List<UserModel> getUsers() throws Exception {
        return userRepo.findAll();
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

    // Update User
    public boolean updateUser(UserRequest userReq) throws Exception {
        try {
            UserModel userModel = userRepo.findById(userReq.getId())
                    .orElseThrow(()->new Exception("Error, User not found!"));
            if(userReq.getName() != null && !userReq.getName().equals("")){
                userModel.setName(userReq.getName());
            }
            if(userReq.getEmail() != null && !userReq.getEmail().equals("")){
                userModel.setEmail(userReq.getEmail());
            }
            if(userReq.getMobile() != null && !userReq.getMobile().equals("")){
                userModel.setMobile(userReq.getMobile());
            }
            if(userReq.getAddress() != null && !userReq.getAddress().equals("")){
                userModel.setAddress(userReq.getAddress());
            }
            userRepo.save(userModel);
                return true;
        } catch (Exception e){
            throw e;
        }
    }

    // Delete User
    public boolean deleteUser(Integer userId) throws Exception{
        UserModel delUser = userRepo.findById(userId).orElseThrow(()->new Exception("Error, User not found!"));
        userRepo.delete(delUser);
        return true;
    }
}
