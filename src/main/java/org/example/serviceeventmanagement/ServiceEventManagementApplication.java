package org.example.serviceeventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "org.example.serviceeventmanagement.client")
@SpringBootApplication(scanBasePackages = "org.example.serviceeventmanagement")
public class ServiceEventManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEventManagementApplication.class, args);
	}

}
