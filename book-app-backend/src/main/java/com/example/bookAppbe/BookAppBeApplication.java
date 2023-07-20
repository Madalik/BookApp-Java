package com.example.bookAppbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BookAppBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppBeApplication.class, args);
	}

}
