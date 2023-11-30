package br.org.rentalcarapi;

import br.org.rentalcarapi.infra.service.AuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class RentalCarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalCarApiApplication.class, args);
	}

	@Bean
	public AuthenticationService authenticationService() {
		return new AuthenticationService();
	}
}
