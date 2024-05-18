package com.projects.queue.repository;

import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    void deleteAllByUserId(Long user_id);

    Question findByUserId(Long user_id);

    Optional<Question> findById(Long id);

    void deleteById(Long id);
}
