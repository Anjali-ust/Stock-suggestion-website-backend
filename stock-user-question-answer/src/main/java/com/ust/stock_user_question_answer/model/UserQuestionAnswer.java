package com.ust.stock_user_question_answer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserQuestionAnswer {
    @Id
    private String  answerId;
    private String userAnswer;
    private String userId;
    private String questionId;

    public UserQuestionAnswer() {
    }

    public UserQuestionAnswer(String answerId, String userAnswer, String userId, String questionId) {
        this.answerId = answerId;
        this.userAnswer = userAnswer;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
