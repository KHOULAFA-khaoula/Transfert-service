package com.ensa.transfertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.ensa.transfertservice")
public class TransfertServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransfertServiceApplication.class, args);
	}

}
