package com.onlinemarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class OnlineMarketPlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMarketPlaceApplication.class, args);
		System.out.println("application started.....");
	}

}
