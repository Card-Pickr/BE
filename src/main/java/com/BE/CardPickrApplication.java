package com.BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class CardPickrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardPickrApplication.class, args);
	}

}
