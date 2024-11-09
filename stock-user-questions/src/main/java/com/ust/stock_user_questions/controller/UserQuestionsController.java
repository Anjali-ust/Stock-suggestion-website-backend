package com.ust.stock_user_questions.controller;

import com.ust.stock_user_questions.client.FullResponse;
import com.ust.stock_user_questions.model.UserQuestions;
import com.ust.stock_user_questions.service.UserQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class UserQuestionsController {
    @Autowired
    private UserQuestionsService userQuestionsService;

    @PostMapping("/save")
    public ResponseEntity<UserQuestions> saveQuestion(@RequestBody UserQuestions question){
        return ResponseEntity.ok().body(userQuestionsService.saveQuestion(question));
    }

    @GetMapping("/get-all-questions")
    public ResponseEntity<List<UserQuestions>> getAllQuestions(){
        return ResponseEntity.ok().body(userQuestionsService.getAllQuestions());
    }

    @GetMapping("/get-question-by-id/{id}")
    public ResponseEntity<UserQuestions> getQuestionById(@PathVariable(name="id") String id){
        Optional<UserQuestions> questionOptional = userQuestionsService.getQuestionById(id);
        if(questionOptional.isPresent()){
            return ResponseEntity.ok().body(questionOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete-question-by-id/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(userQuestionsService.deleteQuestionById(id));
    }
    @PutMapping("/update-question/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable(name="id") String id,@RequestBody UserQuestions question){
        UserQuestions updatedQuestion = userQuestionsService.updateQuestion(id,question);
        if(updatedQuestion == null){
            return ResponseEntity.ok().body("Branch not found");
        }
        else{
            return ResponseEntity.ok().body(updatedQuestion);
        }
    }
    @GetMapping("/get-questions-by-categoryid/{id}")
    public ResponseEntity<List<FullResponse>> getQuestionsByCategoryId(@PathVariable("id") String id){
           List<FullResponse> questions = userQuestionsService.getQuestionsByCategoryId(id);
           if(questions.isEmpty()){
               return ResponseEntity.noContent().build();
           }
           else{
               return ResponseEntity.ok().body(questions);
           }
    }
}
