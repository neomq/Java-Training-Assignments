package wk5.oct7.src.main.java.com.wk5oct7.controller;

import com.wk5oct7.request.CalculateRequest;
import com.wk5oct7.request.LoginRequest;
import com.wk5oct7.response.CalculateResponse;
import com.wk5oct7.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {

    @PostMapping("calculate")
    public ResponseEntity<CalculateResponse> calculate(@RequestBody CalculateRequest cal){

        int result = 0;

        if (cal.getAction().equals("add")){
            result = cal.getNum1() + cal.getNum2();
        } else if (cal.getAction().equals("subtract")){
            result = cal.getNum1() - cal.getNum2();
        } else if (cal.getAction().equals("multiply")) {
            result = cal.getNum1() * cal.getNum2();
        } else if (cal.getAction().equals("divide")) {
            result = cal.getNum1() / cal.getNum2();
        }

        CalculateResponse res = new CalculateResponse();
        res.setResult(""+result);
        return ResponseEntity.ok(res);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login){

        ArrayList<Accounts> userAccounts = new ArrayList<Accounts>();

        Accounts user1 = new Accounts();
        user1.setUsername("johndoe");
        user1.setPassword("12345");

        Accounts user2 = new Accounts();
        user2.setUsername("admin");
        user2.setPassword("admin123");

        Accounts user3 = new Accounts();
        user3.setUsername("tom");
        user3.setPassword("qwerty123");

        userAccounts.add(user1);
        userAccounts.add(user2);
        userAccounts.add(user3);

        boolean match = false;

        for (Accounts account: userAccounts){
            if (login.getUsername().equals(account.getUsername()) &&
                login.getPassword().equals(account.getPassword())) {
                match = true;
            }
        }

        LoginResponse res = new LoginResponse();

        if (match){
             res.setStatus("Login success!");
             return ResponseEntity.ok(res);
        } else {
             res.setStatus("Login error, username or password does not match!");
             return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping("users")
    public ResponseEntity<ArrayList<User>> getUsers(){

        ArrayList<User> userList = new ArrayList<User>();

        User user1 = new User();
        user1.setEmail("hayley@email.com");
        user1.setUsername("hayley");
        user1.setAddress("24 River Valley Close");

        User user2 = new User();
        user2.setEmail("beth@email.com");
        user2.setUsername("Beth");
        user2.setAddress("24 River Valley Close");

        User user3 = new User();
        user3.setEmail("harry@email.com");
        user3.setUsername("harry");
        user3.setAddress("76 Playfair Road");

        User user4 = new User();
        user4.setEmail("eugene@email.com");
        user4.setUsername("Eugene");
        user4.setAddress("6001 Beach Road");

        User user5 = new User();
        user5.setEmail("janice@email.com");
        user5.setUsername("Janice");
        user5.setAddress("Crawford Lane");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

        return ResponseEntity.ok(userList);
    }

}
