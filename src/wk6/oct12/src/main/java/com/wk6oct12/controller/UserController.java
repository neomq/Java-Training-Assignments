package wk6.oct12.src.main.java.com.wk6oct12.controller;

import com.wk6oct12.response.GeneralResponse;
import com.wk6oct12.services.UserService;
import com.wk6oct12.request.UserRequest;
import com.wk6oct12.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("allusers")
    public ResponseEntity<?> allUsers() {
        try{
            List<UserModel> userList = userService.allUsers();
            return ResponseEntity.ok(userList);
        }catch (Exception e){
            GeneralResponse res= new GeneralResponse();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

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

}
