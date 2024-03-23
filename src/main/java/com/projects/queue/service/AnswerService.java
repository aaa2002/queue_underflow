package com.projects.queue.service;

import com.projects.queue.DTOs.answer.AnswerDTO;
import com.projects.queue.DTOs.answer.UpdateAnswerDTO;
import com.projects.queue.model.Answer;

import java.util.List;

public interface AnswerService {

    public void createAnswer(AnswerDTO answerDTO, Long questionId, Long userId);

    public void updateAnswer(UpdateAnswerDTO updateAnswerDTO);

    public void deleteAnswer(Long id);

    public Answer getAnswerById(Long id);

    public List<Answer> getAnswersPerQuestion(Long questionId);
}
