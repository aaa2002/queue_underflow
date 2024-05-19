package com.projects.queue.controller;

import com.projects.queue.DTOs.question.CreateQuestionDTO;
import com.projects.queue.DTOs.question.UpdateQuestionDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import com.projects.queue.service.QuestionService;
import com.projects.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
        User user = userService.getUserByEmail(createQuestionDTO.getUserEmail());
        questionService.createQuestion(createQuestionDTO.getQuestionDTO(), user);
    }

    @PostMapping("/update")
    public void updateQuestion(@RequestBody UpdateQuestionDTO questionDTO) {
        questionService.updateQuestion(questionDTO);
    }

    @Transactional
    @PostMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
    }

    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{questionId}")
    public String getQuestionById(@PathVariable Long questionId) {
        return questionService.getQuestionById(questionId).toString();
    }

    @PostMapping("/like/{questionId}/{userEmail}")
    public ResponseEntity<String> likeQuestion(@PathVariable Long questionId, @PathVariable String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        int result = questionService.likeQuestion(questionId, user);

        switch (result) {
            case 0:
                return ResponseEntity.ok("Question liked");
            case 1:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Question already liked");
            case 2:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot like own question");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error liking question");
        }
    }

    @PostMapping("/dislike/{questionId}/{userEmail}")
    public ResponseEntity<String> dislikeQuestion(@PathVariable Long questionId, @PathVariable String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        int result = questionService.dislikeQuestion(questionId, user);

        switch (result) {
            case 0:
                return ResponseEntity.ok("Question disliked");
            case 1:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Question already disliked");
            case 2:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot dislike own question");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error disliking question");
        }
    }
}
