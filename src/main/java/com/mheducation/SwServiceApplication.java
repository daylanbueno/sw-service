package com.mheducation;

import com.mheducation.exceptions.RetreiveMessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class SwServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SwServiceApplication.class, args);
	}
	@Bean
	public ErrorDecoder errorDecoder() {
		return new RetreiveMessageErrorDecoder();
	}
}
