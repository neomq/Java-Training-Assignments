package wk6.oct13.src.main.java.com.wk6oct13.controller;

import com.wk6oct13.model.UserModel;
import com.wk6oct13.services.UserService;
import com.wk6oct13.request.UserRequest;
import com.wk6oct13.response.GeneralResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    // User Login
    @PostMapping("login")
    public ResponseEntity<?> userlogin(@RequestBody UserRequest userRequest){
        GeneralResponse res = new GeneralResponse();
        try{
            UserModel user = userService.validateUserLogin(userRequest.getEmail(),userRequest.getPassword());
            return ResponseEntity.ok(user);
        }catch (Exception e){
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("logout/{user_id}")
    public ResponseEntity<GeneralResponse> userlogout(@PathVariable Integer user_id){
        GeneralResponse res = new GeneralResponse();
        try{
            userService.userLogout(user_id);
            res.setMessage("Logout successful");
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("allusers")
    public ResponseEntity<?> allUsers(@RequestHeader String token,
                                      @RequestHeader Integer user_id){
        try {
            List<UserModel> list = userService.getUsers();
            return ResponseEntity.ok(list);
        } catch (Exception e){
            GeneralResponse res = new GeneralResponse();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }
}
