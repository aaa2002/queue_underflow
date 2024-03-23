package com.projects.queue.DTOs.answer;

public class UpdateAnswerDTO {
    private Long id;
    private String text;

    public UpdateAnswerDTO() {
    }

    public UpdateAnswerDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
