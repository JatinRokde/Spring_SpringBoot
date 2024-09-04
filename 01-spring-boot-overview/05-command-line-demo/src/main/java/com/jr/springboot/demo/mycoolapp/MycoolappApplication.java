package com.jr.springboot.demo.mycoolapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MycoolappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycoolappApplication.class, args);
	}

//	To run using command-line
//	mvnw package
//	Approach 1: cd target <jar_name>.jar
//	Approach 2: mvnw spring-boot:run
}
