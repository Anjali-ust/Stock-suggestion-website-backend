package com.ust.stock_user_questions.repository;

import com.ust.stock_user_questions.model.UserQuestions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestionsRepository extends MongoRepository<UserQuestions,String> {
    List<UserQuestions> findByCategoryId(String categoryId);
}
