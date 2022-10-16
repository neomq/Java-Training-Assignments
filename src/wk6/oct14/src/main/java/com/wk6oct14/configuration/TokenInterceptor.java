package wk6.oct14.src.main.java.com.wk6oct14.configuration;

import com.wk6oct14.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String current_url = request.getRequestURL().toString();
            if(current_url.endsWith("login") || current_url.contains("imageupload") || current_url.contains("readImage")){
                System.out.println("TokenInterceptor: User is logging in via: "+ current_url + "\nNo checking required.");
                return true;
            }
            String token = request.getHeader("token");
            String userId = request.getHeader("user_id");
            Integer intUserId = Integer.parseInt(userId);
            userService.checkJWTToken(token);
            if(userService.validateToken(token,intUserId)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
