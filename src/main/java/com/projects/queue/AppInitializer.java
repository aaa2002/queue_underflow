package com.projects.queue;

import com.projects.queue.DTOs.question.CreateQuestionDTO;
import com.projects.queue.DTOs.question.QuestionDTO;
import com.projects.queue.DTOs.user.CreateUserDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.Role;
import com.projects.queue.model.User;
import com.projects.queue.service.QuestionService;
import com.projects.queue.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @PostConstruct
    public void initialize() {
        // Check if a default user profile exists
        User existingUser = userService.getUserByEmail("default@example.com");
        if (existingUser == null) {
            CreateUserDTO createUserDTO = new CreateUserDTO();
            createUserDTO.setEmail("aaa");
            createUserDTO.setPassword("aaa");
            createUserDTO.setName("aaa");
            createUserDTO.setRole(Role.MODERATOR);
            createUserDTO.setAvatar("red");
            userService.createUser(createUserDTO);
        }

        QuestionDTO questionDTO = new QuestionDTO("What is the capital of France?", "Hello! I was wondering what the capital of France is. Can someone help me?");
        questionService.createQuestion(questionDTO, userService.getUserByEmail("aaa"));
        questionDTO = new QuestionDTO("What is the capital of England?", "Hello! I was wondering what the capital of England is. Can someone help me?");
        questionService.createQuestion(questionDTO, userService.getUserByEmail("aaa"));
        questionDTO = new QuestionDTO("What is the capital of Romania?", "Hello! I was wondering what the capital of Romania is. Can someone help me?");
        questionService.createQuestion(questionDTO, userService.getUserByEmail("aaa"));
    }
}
