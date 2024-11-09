package com.ust.stock_user_question_answer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserQuestionAnswer {
    @Id
    private String  answerId;
    private String userAnswer;
    private String username;
    private String questionId;

    public UserQuestionAnswer() {
    }

    public UserQuestionAnswer(String answerId, String userAnswer, String username, String questionId) {
        this.answerId = answerId;
        this.userAnswer = userAnswer;
        this.username = username;
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
