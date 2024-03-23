package com.projects.queue.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String title;
    private String text;
    private Date createdAt;
    // LOB: Large Object
    @Lob
    private byte[] image;
    // TODO: Tags
    // TODO: Answers
    // TODO: likers
    // TODO: dislikers

    public Question() {
    }

    public Question(Long id, User user, String title, String text, Date createdAt, byte[] image) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
