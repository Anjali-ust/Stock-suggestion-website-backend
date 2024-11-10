package com.ust.stock_user_question_answer.dto;

import java.util.List;

public class UserQuestionResponseDto {
    private String username;
    private List<QuestionAnswerDto> questions;

    public UserQuestionResponseDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<QuestionAnswerDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAnswerDto> questions) {
        this.questions = questions;
    }
}
