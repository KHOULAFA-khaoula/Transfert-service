package com.ensa.transfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
public class TransfertApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TransfertApplication.class, args);
	}

}
