package com.ust.stock_user_question_answer.service;

import com.ust.stock_user_question_answer.model.UserQuestionAnswer;
import com.ust.stock_user_question_answer.repository.UserQuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserQuestionAnswerService {

    @Autowired
    private UserQuestionAnswerRepository answerRepository;

    public UserQuestionAnswer saveAnswer(UserQuestionAnswer answer){
        return answerRepository.save(answer);
    }

    public List<UserQuestionAnswer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Optional<UserQuestionAnswer> getAnswerById(String id) {
        return answerRepository.findById(id);
    }

    public String deleteAnswerById(String id) {
        Optional<UserQuestionAnswer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            answerRepository.deleteById(id);
            return "Answer deleted";
        } else {
            return "Cannot find Answer";
        }
    }

    public UserQuestionAnswer updateAnswer(String id, UserQuestionAnswer answer) {
        Optional<UserQuestionAnswer> answerOptional = answerRepository.findById(id);
        if (answerOptional.isPresent()) {
            UserQuestionAnswer existingAnswer = answerOptional.get();
            existingAnswer.setAnswerId(answer.getAnswerId());
            existingAnswer.setUserAnswer(answer.getUserAnswer());
            existingAnswer.setUsername(answer.getUsername());
            existingAnswer.setQuestionId(answer.getQuestionId());
            return answerRepository.save(existingAnswer);
        }
        return null;
    }
}
