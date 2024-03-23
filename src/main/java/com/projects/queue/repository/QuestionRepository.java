package com.projects.queue.repository;

import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
