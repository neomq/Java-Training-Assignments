package wk6.oct14.src.main.java.com.wk6oct14.services;

import com.wk6oct14.model.UserModel;
import wk6.oct14.src.main.java.com.wk6oct14.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    Environment env;

    // User login
    public UserModel validateUserLogin(String email,String password) throws Exception{
        UserModel user = userRepo.getUserByEmailAndPassword(email,password).orElseThrow(()->new Exception("Login Error!"));
        String token = generateToken(user); // generate token
        updateToken(token,user.getId());
        user.setToken(token);
        System.out.println("UserService: Token generated.");
        return user;
    }

    // Update token in database
    private void updateToken(String token ,int userId){
        userRepo.updateTokenForUserId(token,userId);
    }

    // User logout
    public boolean userLogout(int user_id) throws Exception{
        updateToken("",user_id);
        System.out.println("UserService: Token removed.");
        return true;
    }

    // Generate token
    private String generateToken(UserModel user) {
        Calendar cal = Calendar.getInstance(); // get today's date time
        cal.add(Calendar.DAY_OF_YEAR, 2); // add 2 days

        String jwtToken = Jwts.builder()
                .claim("email", user.getEmail())
                .setSubject(user.getName())
                .setId(""+user.getId())
                .setIssuedAt(new Date())
                .setExpiration(cal.getTime())
                .signWith(SignatureAlgorithm.HS512, env.getProperty("JWT_SECRET"))
                .compact();
        return jwtToken;
    }

    // Check token
    public boolean checkJWTToken(String token) throws Exception {
        Jwts.parser().setSigningKey(env.getProperty("JWT_SECRET")).parseClaimsJws(token);
        return true;
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
    public boolean validateToken(String token,Integer userId) throws Exception{
        UserModel user = getUser(userId);
        if(user.getToken().equals(token)){
            return true;
        } else {
            throw new Exception("token mismatch");
        }
    }

    // Upload profile_img in database
    private void uploadProfileImg(String profile_img, int userId){
        userRepo.uploadImageForUserId(profile_img, userId);
    }
    public UserModel uploadUserProfileImg(String profile_img, int userId) throws Exception{
        UserModel user = userRepo.getUserById(userId).orElseThrow(()->new Exception("Error!"));
        uploadProfileImg(profile_img,user.getId());
        user.setProfile_img(profile_img);
        return user;
    }

}
