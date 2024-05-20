package com.projects.queue.service;

import com.projects.queue.DTOs.question.QuestionDTO;
import com.projects.queue.DTOs.question.UpdateQuestionDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.Tag;
import com.projects.queue.model.User;

import java.util.List;

public interface QuestionService {
    void createQuestion(QuestionDTO questionDTO, User user);

    void createQuestion(QuestionDTO questionDTO, User user, List<Tag> tags);

    void deleteQuestionById(Long id);

    void updateQuestion(UpdateQuestionDTO updateQuestionDTO);

    Question getQuestionById(Long id);

    List<Question> getAllQuestions();

    int likeQuestion(Long questionId, User user);

    int dislikeQuestion(Long questionId, User user);

    List<Question> getQuestionsByTag(Long tag);
}
