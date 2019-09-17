package com.swap281;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Swap281Application {

    private static final Logger log = LoggerFactory.getLogger(Swap281Application.class);


	public static void main(String[] args) {
		SpringApplication.run(Swap281Application.class, args);
	}


    public void run(String[] args) {

        log.info("StartApplication...");
        // sample test here
    }
}
