package com.projects.queue.service;

import com.projects.queue.DTOs.QuestionDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import com.projects.queue.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void createQuestion(QuestionDTO questionDTO, User user) {
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        question.setText(questionDTO.getText());
        question.setCreatedAt(Instant.now());
        question.setAuthor(user);
        question.setScore(0);

        questionRepository.save(question);
    }
}
