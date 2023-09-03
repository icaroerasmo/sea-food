package com.icaroerasmo.seafood;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class SeafoodBusinessApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeafoodBusinessApplication.class, args);
	}
}
