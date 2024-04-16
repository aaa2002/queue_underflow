package com.projects.queue.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;
    private String text;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonManagedReference
    private Question question;
    //  TODO: image
    private Instant createdAt;
    private Integer score;
    //  TODO: likers
    //  TODO: dislikers

    public void setId(Long id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"user\": " + (user != null ? user.getId() : null) +
                ", \"text\": \"" + text + '\"' +
                ", \"question\": " + (question != null ? question.getId() : null) +
                ", \"createdAt\": \"" + createdAt + '\"' +
                ", \"score\": " + score +
                '}';
    }

}
