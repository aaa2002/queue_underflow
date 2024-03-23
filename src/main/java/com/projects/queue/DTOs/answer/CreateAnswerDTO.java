package com.projects.queue.DTOs.answer;

public class CreateAnswerDTO {
    AnswerDTO answerDTO;
    Long userId;
    Long questionId;

    public CreateAnswerDTO() {
    }

    public CreateAnswerDTO(AnswerDTO answerDTO, Long userId, Long questionId) {
        this.answerDTO = answerDTO;
        this.userId = userId;
        this.questionId = questionId;
    }

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
