package wk6.oct14.src.main.java.com.wk6oct14.controller;

import com.wk6oct14.model.UserModel;
import com.wk6oct14.request.UserRequest;
import com.wk6oct14.response.GeneralResponse;
import com.wk6oct14.services.UserService;
import org.apache.catalina.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    String imgFolderPath = "/Users/minqi/Documents/IdeaProjects/wk6oct14/src/upload_folder/";

    @Autowired
    UserService userService;

    // User Login
    @PostMapping("login")
    public ResponseEntity<?> userlogin(@RequestBody UserRequest userRequest) throws Exception {
        UserModel user = userService.validateUserLogin(userRequest.getEmail(),userRequest.getPassword());
        System.out.println("UserController: Login Success");
        return ResponseEntity.ok(user);
    }

    // User Logout
    @PostMapping("logout/{user_id}")
    public ResponseEntity<GeneralResponse> userlogout(@PathVariable Integer user_id) throws Exception {
        GeneralResponse res = new GeneralResponse();
        userService.userLogout(user_id);
        res.setMessage("Logout successful");
        System.out.println("UserController: Logout Success");
        return ResponseEntity.ok(res);
    }

    // List Users
    @PostMapping("allusers")
    public ResponseEntity<?> allUsers(@RequestHeader String token,
                                      @RequestHeader Integer user_id) throws Exception {
            List<UserModel> list = userService.getUsers();
            return ResponseEntity.ok(list);
    }

    // Upload Profile Image by userID
    @PostMapping("imageupload/{user_id}")
    public ResponseEntity<?> imageUpload(@PathVariable Integer user_id,
                                         @RequestParam MultipartFile file) throws Exception{
        System.out.println(file.getName());
        FileOutputStream out = new FileOutputStream(imgFolderPath + file.getOriginalFilename());
        out.write(file.getBytes());
        UserModel user = userService.uploadUserProfileImg(file.getOriginalFilename(), user_id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(
            value = "readImage/{user_id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public byte[] image(@PathVariable Integer user_id) throws Exception {
        UserModel user = userService.getUser(user_id);
        String filename = user.getProfile_img();
        System.out.println("Getting profile image: " + filename);
        FileInputStream input = new FileInputStream(imgFolderPath + filename);
        return IOUtils.toByteArray(input);
    }

}
