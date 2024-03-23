package com.projects.queue.controller;


import com.projects.queue.DTOs.QuestionCreationDTO;
import com.projects.queue.DTOs.QuestionDTO;
import com.projects.queue.DTOs.UpdateQuestionDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import com.projects.queue.service.QuestionService;
import com.projects.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/update")
    public void updateQuestion(@RequestBody UpdateQuestionDTO questionDTO) {
        questionService.updateQuestion(questionDTO);
    }

    @PostMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
    }

    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
