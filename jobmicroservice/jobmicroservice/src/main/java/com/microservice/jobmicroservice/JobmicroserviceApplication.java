package com.microservice.jobmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobmicroserviceApplication.class, args);
		System.out.println("job run successfully");
	}

}
