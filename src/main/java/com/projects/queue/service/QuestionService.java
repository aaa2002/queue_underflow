package com.projects.queue.service;

import com.projects.queue.DTOs.QuestionDTO;
import com.projects.queue.model.User;

public interface QuestionService {
    void createQuestion(QuestionDTO questionDTO, User user);
}
