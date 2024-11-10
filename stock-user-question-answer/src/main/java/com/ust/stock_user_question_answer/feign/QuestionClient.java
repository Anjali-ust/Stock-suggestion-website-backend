package com.ust.stock_user_question_answer.feign;

import com.ust.stock_user_question_answer.client.UserQuestions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="stock-user-questions", url="http://localhost:7064/questions")
public interface QuestionClient {
    @GetMapping("/get-question-by-id/{id}")
    public Optional<UserQuestions> getQuestionById(@PathVariable("id") String id);
}
