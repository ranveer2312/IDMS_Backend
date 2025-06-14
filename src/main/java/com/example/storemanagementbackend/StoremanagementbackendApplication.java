package com.example.storemanagementbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan("com.example.storemanagementbackend")
@EntityScan(basePackages = {"com.example.storemanagementbackend.entity", "com.example.storemanagementbackend.model"})
public class StoremanagementbackendApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(StoremanagementbackendApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}

}
