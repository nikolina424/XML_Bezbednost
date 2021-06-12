package services.agentappservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AgentAppServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentAppServicesApplication.class, args);
	}

}
