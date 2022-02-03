package com.ensa.notificationSMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NotificationSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationSmsApplication.class, args);
	}

}
