package wk6.oct12.src.main.java.com.wk6oct12.services;

import wk6.oct12.src.main.java.com.wk6oct12.repository.UserRepo;
import com.wk6oct12.model.UserModel;
import com.wk6oct12.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<UserModel> allUsers() throws Exception {
        return userRepo.findAll();
    }

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


}
