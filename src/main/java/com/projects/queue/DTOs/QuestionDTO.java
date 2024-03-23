package com.projects.queue.DTOs;

import java.util.List;

public class QuestionDTO {
    private String title;
    private String text;
    // TODO: private List<String> tags;

    public QuestionDTO() {
    }

    public QuestionDTO(String title, String description, Long author, String authorEmail) {
        this.title = title;
        this.text = description;
        //this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
