package com.projects.queue.controller;

import com.projects.queue.DTOs.answer.AnswerDTO;
import com.projects.queue.DTOs.answer.CreateAnswerDTO;
import com.projects.queue.DTOs.answer.UpdateAnswerDTO;
import com.projects.queue.model.Answer;
import com.projects.queue.model.User;
import com.projects.queue.service.AnswerService;
import com.projects.queue.service.QuestionService;
import com.projects.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/like/{answerId}/{userEmail}")
    public ResponseEntity<String> likeAnswer(@PathVariable Long answerId, @PathVariable String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        int result = answerService.likeAnswer(answerId, user);

        switch (result) {
            case 0:
                return ResponseEntity.ok("Answer liked");
            case 1:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Answer already liked");
            case 2:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot like own answer");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error liking answer");
        }
    }

    @PostMapping("/dislike/{answerId}/{userEmail}")
    public ResponseEntity<String> dislikeAnswer(@PathVariable Long answerId, @PathVariable String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        int result = answerService.dislikeAnswer(answerId, user);

        switch (result) {
            case 0:
                return ResponseEntity.ok("Answer disliked");
            case 1:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Answer already disliked");
            case 2:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot dislike own answer");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error disliking answer");
        }
    }
}
