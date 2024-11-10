package com.ust.stock_user_question_answer.service;


import com.ust.stock_user_question_answer.client.UserQuestions;
import com.ust.stock_user_question_answer.dto.QuestionAnswerDto;
import com.ust.stock_user_question_answer.dto.UserQuestionResponseDto;
import com.ust.stock_user_question_answer.feign.QuestionClient;
import com.ust.stock_user_question_answer.model.UserQuestionAnswer;
import com.ust.stock_user_question_answer.repository.UserQuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserQuestionAnswerService {

    @Autowired
    private UserQuestionAnswerRepository answerRepository;

    @Autowired
    private QuestionClient questionClient;

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

    //To display the user questions along with the chosen answer
    public UserQuestionResponseDto getUserQuestionsWithAnswers(String username) {
        List<UserQuestionAnswer> userAnswers = answerRepository.findByUsername(username);
        // For each answer, get the question details
        List<QuestionAnswerDto> questionsWithAnswers = userAnswers.stream().map(answer -> {
            UserQuestions question = questionClient.getQuestionById(answer.getQuestionId())
                    .get();

            QuestionAnswerDto dto = new QuestionAnswerDto();
            dto.setQuestion(question.getQuestionDescription());
            dto.setOptionA(question.getOptionA());
            dto.setOptionB(question.getOptionB());
            dto.setOptionC(question.getOptionC());
            dto.setOptionD(question.getOptionD());
            dto.setChosenAnswer(answer.getUserAnswer());

            return dto;
        }).toList();

        UserQuestionResponseDto response = new UserQuestionResponseDto();
        response.setUsername(username);
        response.setQuestions(questionsWithAnswers);

        return response;

    }
}
