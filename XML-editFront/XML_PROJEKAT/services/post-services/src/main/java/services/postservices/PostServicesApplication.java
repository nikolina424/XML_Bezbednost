package services.postservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PostServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostServicesApplication.class, args);
	}

}
