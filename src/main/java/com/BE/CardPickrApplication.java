package com.BE;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CardPickrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardPickrApplication.class, args);
	}

}
