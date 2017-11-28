package com.knucker.connectorMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.knucker.connectorMaster"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class SocialConnectorApp {

	public static void main(String[] args) {
		SpringApplication.run(SocialConnectorApp.class, args);
	}
}
