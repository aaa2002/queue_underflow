package com.projects.queue.service;

import com.projects.queue.DTOs.QuestionDTO;
import com.projects.queue.DTOs.UpdateQuestionDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import com.projects.queue.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void createQuestion(QuestionDTO questionDTO, User user) {
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        question.setText(questionDTO.getText());
        question.setCreatedAt(Instant.now());
        question.setUser(user);
        question.setScore(0);

        questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
        System.out.println("Question with id " + id + " deleted");
    }

    public void updateQuestion(UpdateQuestionDTO updateQuestionDTO) {
        Question question = questionRepository.findById(updateQuestionDTO.getId()).get();
        question.setTitle(updateQuestionDTO.getTitle());
        question.setText(updateQuestionDTO.getText());

        questionRepository.save(question);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
