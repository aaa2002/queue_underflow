package com.projects.queue.DTOs.question;

import com.projects.queue.model.Tag;

import java.util.List;

public class CreateQuestionDTO {
    public QuestionDTO questionDTO;
    public String userEmail;
    public List<Tag> tags;

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
