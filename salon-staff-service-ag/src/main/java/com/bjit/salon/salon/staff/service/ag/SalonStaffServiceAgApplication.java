package com.bjit.salon.salon.staff.service.ag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SalonStaffServiceAgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalonStaffServiceAgApplication.class, args);
	}

}
