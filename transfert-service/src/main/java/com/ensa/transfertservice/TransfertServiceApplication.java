package com.ensa.transfertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TransfertServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransfertServiceApplication.class, args);
	}

}
