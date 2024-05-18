package com.projects.queue.service;

import com.projects.queue.DTOs.answer.AnswerDTO;
import com.projects.queue.DTOs.answer.UpdateAnswerDTO;
import com.projects.queue.model.Answer;
import com.projects.queue.model.User;
import com.projects.queue.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    public void createAnswer(AnswerDTO answerDTO, Long questionId, Long userId) {
        Answer answer = new Answer();
        answer.setText(answerDTO.getText());
        answer.setQuestion(questionService.getQuestionById(questionId));
        answer.setUser(userService.getUserById(userId));
        answer.setCreatedAt(Instant.now());
        answer.setScore(0);
        answerRepository.save(answer);
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new RuntimeException("Answer not found"));
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public void updateAnswer(UpdateAnswerDTO updateAnswerDTO) {
        Answer answer = getAnswerById(updateAnswerDTO.getId());
        answer.setText(updateAnswerDTO.getText());
        answerRepository.save(answer);
    }

    public List<Answer> getAnswersPerQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public int likeAnswer(Long answerId, User user) {
        try {
            Answer answer = getAnswerById(answerId);

            if (answer.getUser().getId().equals(user.getId())) {
                // User tries to like own answer
                return 2;
            }

            if (answer.getDislikers().contains(user)) {
                answer.getDislikers().remove(user);
                answer.setScore(answer.getScore() + 1);
            }

            if (!answer.getLikers().contains(user)) {
                answer.getLikers().add(user);
                answer.setScore(answer.getScore() + 1);
                answerRepository.save(answer);
                return 0; // Success
            } else {
                // Already liked
                return 1;
            }
        } catch (Exception e) {
            // Error
            return -1;
        }
    }

    public int dislikeAnswer(Long answerId, User user) {
        try {
            Answer answer = getAnswerById(answerId);

            if (answer.getUser().getId().equals(user.getId())) {
                // User tries to dislike own answer
                return 2;
            }

            if (answer.getLikers().contains(user)) {
                answer.getLikers().remove(user);
                answer.setScore(answer.getScore() - 1);
            }

            if (!answer.getDislikers().contains(user)) {
                answer.getDislikers().add(user);
                answer.setScore(answer.getScore() - 1);
                answerRepository.save(answer);
                return 0; // Success
            } else {
                // Already disliked
                return 1;
            }
        } catch (Exception e) {
            // Error
            return -1;
        }
    }
}
