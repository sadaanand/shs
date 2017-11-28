package com.knucker.fb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.knucker.fb"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class FaceBookConnectorApp {

	public static void main(String[] args) {
		SpringApplication.run(FaceBookConnectorApp.class, args);
	}
}
