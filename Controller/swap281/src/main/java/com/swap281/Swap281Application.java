package com.swap281;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.swap281")
@EnableJpaRepositories(basePackages = "com.swap281.repository")
@EntityScan(basePackages = "com.swap281.model")
public class Swap281Application {

	public static void main(String[] args) {
		SpringApplication.run(Swap281Application.class, args);
	}
	
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/user/account/username-check").allowedOrigins("http://localhost:8080");
            }
        };
    }
}
