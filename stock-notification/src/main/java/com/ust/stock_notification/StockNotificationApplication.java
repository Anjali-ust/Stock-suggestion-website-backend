package com.ust.stock_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class StockNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockNotificationApplication.class, args);
	}

}
