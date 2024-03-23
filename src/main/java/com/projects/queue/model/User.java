package com.projects.queue.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private Integer score;
    private Role role;
    private AccountStatus accountStatus;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Question> questions;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Answer> answers;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
