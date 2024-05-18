package com.projects.queue.repository;

import com.projects.queue.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long question_id);

    void deleteByQuestionId(Long question_id);

    void deleteAllByUserId(Long user_id);
}
