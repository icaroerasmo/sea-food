package com.icaroerasmo.seafood;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Log4j2
@EnableCaching
@SpringBootApplication
public class SeafoodBusinessApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeafoodBusinessApplication.class, args);
	}
}
