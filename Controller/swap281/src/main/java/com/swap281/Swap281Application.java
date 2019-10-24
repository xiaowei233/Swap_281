package com.swap281;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ServletComponentScan
//@SpringBootApplication(scanBasePackages = "com.swap281")
//@EnableJpaRepositories(basePackages = "com.swap281.repository")
//@EntityScan(basePackages = "com.swap281.model.item")
@SpringBootApplication
public class Swap281Application {

	public static void main(String[] args) {
		SpringApplication.run(Swap281Application.class, args);
	}
}
