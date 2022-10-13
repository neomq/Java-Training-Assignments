package wk6.oct12.src.main.java.com.wk6oct12.controller;

import com.wk6oct12.response.GeneralResponse;
import com.wk6oct12.services.UserService;
import com.wk6oct12.request.UserRequest;
import com.wk6oct12.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    // Register User
    @PostMapping("register")
    public ResponseEntity<GeneralResponse> userRegister(@RequestBody UserRequest userReq) {
        GeneralResponse res = new GeneralResponse();
        try{
            userService.registerUser(userReq);
            res.setMessage("User Created!");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    // User login
    @PostMapping("login")
    public ResponseEntity<?> userlogin(@RequestBody UserRequest userRequest){
        GeneralResponse res = new GeneralResponse();
        try{
            UserModel user = userService.validateUserLogin(userRequest.getEmail(),userRequest.getPassword());
            // res.setMessage("Login Success!");
            return ResponseEntity.ok(user);
        }catch (Exception e){
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    // Get All users
    @GetMapping("allusers")
    public ResponseEntity<?> allUsers() {
        try{
            List<UserModel> userList = userService.getUsers();
            return ResponseEntity.ok(userList);
        }catch (Exception e){
            GeneralResponse res= new GeneralResponse();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    // Get User by ID
    @GetMapping("allusers/{user_id}")
    public ResponseEntity<?> userById(@PathVariable Integer user_id) {
        try{
            UserModel userModel = userService.getUser(user_id);
            return ResponseEntity.ok(userModel);
        }catch (Exception e){
            GeneralResponse res = new GeneralResponse();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    // Update User
    @PostMapping("update")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userReq){
        GeneralResponse res = new GeneralResponse();
        try{
            userService.updateUser(userReq);
            res.setMessage("User successfully updated!");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    // Delete User
    @PostMapping("delete/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer user_id){
        GeneralResponse res = new GeneralResponse();
        try{
            userService.deleteUser(user_id);
            res.setMessage("User is deleted");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }


}
