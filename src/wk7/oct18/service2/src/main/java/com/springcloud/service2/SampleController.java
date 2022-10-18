package wk7.oct18.service2.src.main.java.com.springcloud.service2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service2")
public class SampleController {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("hello")
    public String getHello() {
        return "hello from service 2";
    }

}

