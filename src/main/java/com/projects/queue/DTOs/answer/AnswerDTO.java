package com.projects.queue.DTOs.answer;

public class AnswerDTO {
    private String text;

    public AnswerDTO() {
    }

    public AnswerDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
