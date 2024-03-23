package com.projects.queue.controller;


import com.projects.queue.DTOs.QuestionCreationDTO;
import com.projects.queue.DTOs.QuestionDTO;
import com.projects.queue.model.User;
import com.projects.queue.service.QuestionService;
import com.projects.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createQuestion(@RequestBody QuestionCreationDTO questionCreationDTO) {
        User user = userService.getUserById(questionCreationDTO.userId);

        questionService.createQuestion(questionCreationDTO.questionDTO, user);
    }
}
