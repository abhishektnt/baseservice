package com.gravity.fastcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
public class BaseprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseprojectApplication.class, args);
	}
}
