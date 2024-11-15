package com.ust.stock_user_questions.service;

import com.ust.stock_user_questions.client.Category;
import com.ust.stock_user_questions.client.FullResponse;
import com.ust.stock_user_questions.feign.CategoryClient;
import com.ust.stock_user_questions.model.UserQuestions;
import com.ust.stock_user_questions.repository.UserQuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserQuestionsService {

    @Autowired
    private UserQuestionsRepository userQuestionsRepository;

    @Autowired
    private CategoryClient categoryClient;

    public UserQuestions saveQuestion(UserQuestions question) {
        return userQuestionsRepository.save(question);
    }

    public List<UserQuestions> getAllQuestions() {
        return userQuestionsRepository.findAll();
    }

    public Optional<UserQuestions> getQuestionById(String id) {
        return userQuestionsRepository.findById(id);
    }

    public String deleteQuestionById(String id) {
        Optional<UserQuestions> question = userQuestionsRepository.findById(id);
        if (question.isPresent()) {
            userQuestionsRepository.deleteById(id);
            return "Question deleted";
        } else {
            return "Cannot find Question";
        }
    }

    public UserQuestions updateQuestion(String id, UserQuestions question) {
        Optional<UserQuestions> questionOptional = userQuestionsRepository.findById(id);
        if (questionOptional.isPresent()) {
            UserQuestions existingQuestion = questionOptional.get();
            existingQuestion.setQuestionId(question.getQuestionId());
            existingQuestion.setQuestionDescription(question.getQuestionDescription());
            existingQuestion.setOptionA(question.getOptionA());
            existingQuestion.setOptionB(question.getOptionB());
            existingQuestion.setOptionC(question.getOptionC());
            existingQuestion.setOptionD(question.getOptionD());
            existingQuestion.setCategoryId(question.getCategoryId());
            return userQuestionsRepository.save(existingQuestion);
        }
        return null;
    }

    public List<FullResponse> getQuestionsByCategoryId(String categoryId){
        List<UserQuestions> questions = userQuestionsRepository.findByCategoryId(categoryId);
        Optional<Category> categoryOptional = categoryClient.getCategoryById(categoryId);
        List<FullResponse> responses = new ArrayList<>();
        if(categoryOptional.isPresent()){
             String categoryName = categoryOptional.get().getCategoryName();
             for(UserQuestions question : questions){
                 responses.add(new FullResponse(question.getQuestionId()
                         ,question.getQuestionDescription()
                 ,question.getOptionA()
                 ,question.getOptionB()
                 ,question.getOptionC()
                         ,question.getOptionD()
                 ,question.getCategoryId()
                 ,categoryName));
             }
        }
        return responses;
    }
}
