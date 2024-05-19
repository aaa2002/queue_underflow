package com.projects.queue.repository;

import com.projects.queue.model.Question;
import com.projects.queue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    void deleteAllByUserId(Long user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM question_likers WHERE user_id = :userId", nativeQuery = true)
    void deleteAllLikesByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM question_dislikers WHERE user_id = :userId", nativeQuery = true)
    void deleteAllDislikesByUserId(@Param("userId") Long userId);

    Question findByUserId(Long user_id);

    Optional<Question> findById(Long id);

    void deleteById(Long id);
}
