package com.github.rluisb.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SessionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionServiceApplication.class, args);
	}

}
