package com.ust.stock_user_questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
public class StockUserQuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockUserQuestionsApplication.class, args);
	}

}
