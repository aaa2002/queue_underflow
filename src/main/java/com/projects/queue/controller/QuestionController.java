package com.projects.queue.controller;


import com.projects.queue.DTOs.question.CreateQuestionDTO;
import com.projects.queue.DTOs.question.UpdateQuestionDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import com.projects.queue.service.QuestionService;
import com.projects.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createQuestion(@RequestBody CreateQuestionDTO createQuestionDTO) {
        User user = userService.getUserById(createQuestionDTO.userId);

        questionService.createQuestion(createQuestionDTO.questionDTO, user);
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

    @GetMapping("/{questionId}")
    public Question getQuestionById(@PathVariable Long questionId) {
        return questionService.getQuestionById(questionId);
    }
}
