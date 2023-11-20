package br.org.rentalcarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class RentalCarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalCarApiApplication.class, args);
	}

}
