package com.projects.queue.repository;

import com.projects.queue.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long question_id);

    void deleteByQuestionId(Long question_id);

    void deleteAllByUserId(Long user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM answer_likers WHERE user_id = :userId", nativeQuery = true)
    void deleteAllLikesByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM answer_dislikers WHERE user_id = :userId", nativeQuery = true)
    void deleteAllDislikesByUserId(@Param("userId") Long userId);
}
