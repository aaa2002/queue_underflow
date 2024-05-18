package com.projects.queue.service;

import com.projects.queue.DTOs.question.QuestionDTO;
import com.projects.queue.DTOs.question.UpdateQuestionDTO;
import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import com.projects.queue.repository.AnswerRepository;
import com.projects.queue.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void createQuestion(QuestionDTO questionDTO, User user) {
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        question.setText(questionDTO.getText());
        question.setCreatedAt(Instant.now());
        question.setUser(user);
        question.setScore(0);

        questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));

        question.getLikers().clear();
        question.getDislikers().clear();

        questionRepository.save(question);

        answerRepository.deleteByQuestionId(id);

        questionRepository.deleteById(id);

        System.out.println("Question with id " + id + " deleted");
    }

    public void updateQuestion(UpdateQuestionDTO updateQuestionDTO) {
        Question question = questionRepository.findById(updateQuestionDTO.getId()).orElseThrow(() -> new RuntimeException("Question not found"));
        question.setTitle(updateQuestionDTO.getTitle());
        question.setText(updateQuestionDTO.getText());

        questionRepository.save(question);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public int likeQuestion(Long questionId, User user) {
        try {
            Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));

            if (question.getUser().getId().equals(user.getId())) {
                // tries to like own question
                return 2;
            }

            if (question.getDislikers().contains(user)) {
                question.getDislikers().remove(user);
                question.setScore(question.getScore() + 1);
            }

            if (!question.getLikers().contains(user)) {
                question.getLikers().add(user);
                question.setScore(question.getScore() + 1);
                questionRepository.save(question);
                // success
                return 0;
            } else {
                // already liked
                return 1;
            }
        } catch (Exception e) {
            // error
            return -1;
        }
    }

    public int dislikeQuestion(Long questionId, User user) {
        try {
            Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));

            if (question.getUser().getId().equals(user.getId())) {
                // tries to dislike own question
                return 2;
            }

            if (question.getLikers().contains(user)) {
                question.getLikers().remove(user);
                question.setScore(question.getScore() - 1);
            }

            if (!question.getDislikers().contains(user)) {
                question.getDislikers().add(user);
                question.setScore(question.getScore() - 1);
                questionRepository.save(question);
                // success
                return 0;
            } else {
                // already disliked
                return 1;
            }
        } catch (Exception e) {
            // error
            return -1;
        }
    }

}
