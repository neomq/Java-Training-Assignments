package wk7.oct18.service1.src.main.java.com.springcloud.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("service1")
public class SampleController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("gethello")
    public String getHello(){
        String response = restTemplate.getForObject("http://localhost:8081/service2/hello", String.class);
        System.out.println("response from service 2: " + response);
        return "OK";
    }

    private String callMeIfError(){
        return "Success response, with error.";
    }

    @GetMapping("goodbye")
    public String getGoodbye() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String url = "https://localhost:1234/not-real";
        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class),
                throwable ->callMeIfError());
    }
}
