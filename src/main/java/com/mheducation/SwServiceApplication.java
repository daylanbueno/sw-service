package com.mheducation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SwServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwServiceApplication.class, args);
	}

}
