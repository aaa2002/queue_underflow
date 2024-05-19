package com.projects.queue.DTOs.answer;

public class CreateAnswerDTO {
    AnswerDTO answerDTO;
    String userEmail;
    Long questionId;

    public CreateAnswerDTO() {
    }

    public CreateAnswerDTO(AnswerDTO answerDTO, String userEmail, Long questionId) {
        this.answerDTO = answerDTO;
        this.userEmail = userEmail;
        this.questionId = questionId;
    }

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
