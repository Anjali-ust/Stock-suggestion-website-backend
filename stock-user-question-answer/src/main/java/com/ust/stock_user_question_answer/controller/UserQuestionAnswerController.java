package com.ust.stock_user_question_answer.controller;

import com.ust.stock_user_question_answer.dto.UserQuestionResponseDto;
import com.ust.stock_user_question_answer.model.UserQuestionAnswer;
import com.ust.stock_user_question_answer.service.UserQuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping("/answer")
public class UserQuestionAnswerController {

    @Autowired
    private UserQuestionAnswerService answerService;

    @PostMapping("/save")
    public ResponseEntity<UserQuestionAnswer> saveAnswer(@RequestBody UserQuestionAnswer answer){
        return ResponseEntity.ok().body(answerService.saveAnswer(answer));
    }

    @GetMapping("/get-all-answers")
    public ResponseEntity<List<UserQuestionAnswer>> getAllAnswers(){
        return ResponseEntity.ok().body(answerService.getAllAnswers());
    }

    @GetMapping("/get-answer-by-id/{id}")
    public ResponseEntity<UserQuestionAnswer> getAnswerById(@PathVariable(name="id") String id){
        Optional<UserQuestionAnswer> answerOptional = answerService.getAnswerById(id);
        if(answerOptional.isPresent()){
            return ResponseEntity.ok().body(answerOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete-answer-by-id/{id}")
    public ResponseEntity<String> deleteAnswerById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(answerService.deleteAnswerById(id));
    }
    @PutMapping("/update-answer/{id}")
    public ResponseEntity<?> updateAnswer(@PathVariable(name="id") String id,@RequestBody UserQuestionAnswer answer){
        UserQuestionAnswer updatedAnswer = answerService.updateAnswer(id,answer);
        if(updatedAnswer == null){
            return ResponseEntity.ok().body("Branch not found");
        }
        else{
            return ResponseEntity.ok().body(updatedAnswer);
        }
    }

    //endpoint to get questions with answer given the username
    @GetMapping("/get-questions-with-answers/{username}")
    public UserQuestionResponseDto getUserQuestionsWithAnswers(@PathVariable("username") String username) {
        return answerService.getUserQuestionsWithAnswers(username);
    }
}
