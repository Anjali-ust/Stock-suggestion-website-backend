package com.ust.stock_user_question_answer.repository;

import com.ust.stock_user_question_answer.model.UserQuestionAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionAnswerRepository extends MongoRepository<UserQuestionAnswer,String> {
}
