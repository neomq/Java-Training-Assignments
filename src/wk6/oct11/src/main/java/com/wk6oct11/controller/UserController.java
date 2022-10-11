package wk6.oct11.src.main.java.com.wk6oct11.controller;

import com.wk6oct11.request.LoginRequest;
import com.wk6oct11.response.LoginResponse;
import com.wk6oct11.response.UserInfoResponse;
import com.wk6oct11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginReq){

        LoginResponse res = new LoginResponse();
        try{
            userService.validateUserLogin(loginReq.getUsername(),loginReq.getPassword());
            res.setStatus("Login success!");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setStatus("Login error, username or password does not match!");
            return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping("{user_id}")
    public ResponseEntity<?> getUser(@PathVariable Integer user_id){

        String name = userService.getUserDetail(user_id);
        UserInfoResponse res = new UserInfoResponse();
        if (name == null){
            res.setUserDetail("user not found!");
            return ResponseEntity.badRequest().body(res);
        } else {
            res.setUserDetail("user id "+user_id+" : "+name);
            return ResponseEntity.ok(res);
        }
    }

    @PostMapping("login2")
    public ResponseEntity<?> login2(@RequestBody LoginRequest loginReq) {
        LoginResponse res = new LoginResponse();
        try{
            userService.validateUserLogin2(loginReq.getEmail(),loginReq.getPassword());
            res.setStatus("Success!");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setStatus("Error, please try again");
            return ResponseEntity.badRequest().body(res);
        }
    }


}
