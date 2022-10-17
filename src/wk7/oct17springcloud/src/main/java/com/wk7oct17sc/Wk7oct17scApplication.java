package wk7.oct17springcloud.src.main.java.com.wk7oct17sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Wk7oct17scApplication {

	public static void main(String[] args) {
		SpringApplication.run(Wk7oct17scApplication.class, args);
	}

}
