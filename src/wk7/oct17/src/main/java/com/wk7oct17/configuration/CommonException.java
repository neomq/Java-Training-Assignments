package wk7.oct17.src.main.java.com.wk7oct17.configuration;

import com.wk7oct17.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonException {

    // common exception handler in spring boot application

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        System.out.println("CommonException: " + e.getMessage());
        GeneralResponse res = new GeneralResponse();
        res.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleException(CustomException e){
        System.out.println("CustomException: " + e.getMessage());
        GeneralResponse res = new GeneralResponse();
        res.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(res);
    }


}
