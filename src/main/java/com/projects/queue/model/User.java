package com.projects.queue.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

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
    // TODO: add questions and answers

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
