package com.projects.queue.DTOs;

public class QuestionDTO {
    private String title;
    private String description;
    private String author;
    private String authorEmail;
    private String tags;

    public QuestionDTO() {
    }

    public QuestionDTO(String title, String description, String author, String authorEmail, String tags) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.authorEmail = authorEmail;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getTags() {
        return tags;
    }
}
