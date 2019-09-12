package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentAppApplication {

	public static void main(String[] args) {
		System.out.println("Hello Sring Boot World");
		SpringApplication.run(StudentAppApplication.class, args);
	}
}
