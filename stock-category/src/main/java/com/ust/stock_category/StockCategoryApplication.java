package com.ust.stock_category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class StockCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockCategoryApplication.class, args);
	}

}
