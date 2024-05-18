package com.projects.queue.controller;

import com.projects.queue.DTOs.answer.AnswerDTO;
import com.projects.queue.DTOs.answer.CreateAnswerDTO;
import com.projects.queue.DTOs.answer.UpdateAnswerDTO;
import com.projects.queue.model.Answer;
import com.projects.queue.service.AnswerService;
import com.projects.queue.service.QuestionService;
import com.projects.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createAnswer(@RequestBody CreateAnswerDTO createAnswerDTO) {
        answerService.createAnswer(createAnswerDTO.getAnswerDTO(), createAnswerDTO.getQuestionId(), createAnswerDTO.getUserId());
    }

    @PostMapping("/update")
    public void updateAnswer(@RequestBody UpdateAnswerDTO updateAnswerDTO) {
        answerService.updateAnswer(updateAnswerDTO);
    }

    @Transactional
    @PostMapping("/delete/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }

    @GetMapping("/getAnswersPerQuestion/{questionId}")
    public List<Answer> getAnswersPerQuestion(@PathVariable Long questionId) {
        return answerService.getAnswersPerQuestion(questionId);
    }
}
