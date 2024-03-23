package com.projects.queue.DTOs;

import com.projects.queue.model.AccountStatus;
import com.projects.queue.model.Role;

public class UpdateUserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private Integer score;
    private Role role;
    private AccountStatus accountStatus;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(Long id, String name, String email, String password, String bio, Integer score, Role role, AccountStatus accountStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.score = score;
        this.role = role;
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public Integer getScore() {
        return score;
    }

    public Role getRole() {
        return role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }
}
