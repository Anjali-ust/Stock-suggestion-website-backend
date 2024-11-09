package com.ust.stock_user_question_answer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
public class StockUserQuestionAnswerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockUserQuestionAnswerApplication.class, args);
	}

}
